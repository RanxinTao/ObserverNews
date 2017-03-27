package com.rtao.observernews.menuDetailPage.tabDetailPage;

import android.content.Context;;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rtao.observernews.R;
import com.rtao.observernews.base.BasePage;
import com.rtao.observernews.base.MenuDetailBasePage;
import com.rtao.observernews.domain.NewsPageBean;
import com.rtao.observernews.domain.TabDetailPageBean;
import com.rtao.observernews.utils.CacheUtils;
import com.rtao.observernews.utils.Constants;
import com.rtao.observernews.utils.LogUtil;

import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

public class TabDetailPage extends MenuDetailBasePage {

    private ViewPager viewPager;
    private TextView tv_title;
    private LinearLayout ll_point_group;
    private ListView listView;
    private final NewsPageBean.DetailPageData.ChildrenData childrenData;
    private String url;
    private List<TabDetailPageBean.DataEntity.TopnewsData> topNews;
    // the position of highlighted point
    private int prePosition;
    private List<TabDetailPageBean.DataEntity.NewsData> news;
    private TabDetailPageListAdapter adapter;

    public TabDetailPage(Context context, NewsPageBean.DetailPageData.ChildrenData childrenData) {
        super(context);
        this.childrenData = childrenData;
    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.tab_detail_page, null);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        ll_point_group = (LinearLayout) view.findViewById(R.id.ll_point_group);
        listView = (ListView) view.findViewById(R.id.listview);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        url = Constants.BASE_URL + childrenData.getUrl();
        String cache = CacheUtils.getString(context, url);
        if (!TextUtils.isEmpty(cache)) {
            processData(cache);
        }
        //LogUtil.e(childrenData.getTitle() + " URL: " + url);
        getDataFromNet();
    }

    private void processData(String json) {
        TabDetailPageBean bean = parseJson(json);
        LogUtil.e(bean.getData().getNews().get(0).getTitle());

        topNews = bean.getData().getTopnews();
        // set ViewPager adapter
        viewPager.setAdapter(new TabDetailPageTopNewsAdapter());

        // remove all points
        ll_point_group.removeAllViews();

        for (int i = 0; i < topNews.size(); i++) {
            ImageView imageView = new ImageView(context);
            imageView.setBackgroundResource(R.drawable.point_selector);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtil.dip2px(5),
                    DensityUtil.dip2px(5));

            if (i == 0) {
                imageView.setEnabled(true);
            } else {
                imageView.setEnabled(false);
                params.leftMargin = DensityUtil.dip2px(8);
            }

            imageView.setLayoutParams(params);
            ll_point_group.addView(imageView);
        }

        // add OnPageChangeListener, update the status of red points and text data
        viewPager.addOnPageChangeListener(new OnPageChangeListener());
        tv_title.setText(topNews.get(0).getTitle());

        // get data related to the ListView
        news = bean.getData().getNews();
        // set ListView adapter
        adapter = new TabDetailPageListAdapter();
        listView.setAdapter(adapter);
    }

    class TabDetailPageListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return news.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            viewHolder viewHolder;
            if (convertView == null) {
                convertView = viewPager.inflate(context, R.layout.item_tab_detail_page, null);
                viewHolder = new viewHolder();
                viewHolder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
                viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
                viewHolder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (TabDetailPage.viewHolder) convertView.getTag();
            }

            TabDetailPageBean.DataEntity.NewsData newsData = news.get(position);
            String imageURL = Constants.BASE_URL + newsData.getListimage();
            x.image().bind(viewHolder.iv_icon, imageURL);
            viewHolder.tv_title.setText(newsData.getTitle());
            viewHolder.tv_time.setText(newsData.getPubdate());

            return convertView;
        }
    }

    static class viewHolder {
        ImageView iv_icon;
        TextView tv_title;
        TextView tv_time;
    }

    class OnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int curPosition) {
            // 1. set the text
            tv_title.setText(topNews.get(curPosition).getTitle());
            // 2. highlight the corresponding red point
            ll_point_group.getChildAt(prePosition).setEnabled(false);
            ll_point_group.getChildAt(curPosition).setEnabled(true);
            prePosition = curPosition;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    class TabDetailPageTopNewsAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return topNews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(imageView);

            TabDetailPageBean.DataEntity.TopnewsData topnewsData = topNews.get(position);
            String imageUrl = Constants.BASE_URL + topnewsData.getTopimage();

            // request image from Internet
            x.image().bind(imageView, imageUrl);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    private TabDetailPageBean parseJson(String json) {
        return new Gson().fromJson(json, TabDetailPageBean.class);
    }

    private void getDataFromNet() {
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                // cache data
                CacheUtils.putString(context, url, result);
                LogUtil.e(childrenData.getTitle() + " page data requesting successful " +
                        result);
                processData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e(childrenData.getTitle() + " page data requesting failed " +
                        ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtil.e(childrenData.getTitle() + " page data requesting cancelled " +
                        cex.getMessage());
            }

            @Override
            public void onFinished() {
                LogUtil.e(childrenData.getTitle() + " page data requesting finished ");
            }
        });
    }
}
