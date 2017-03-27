package com.rtao.observernews.page;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.solver.Cache;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rtao.observernews.activity.MainActivity;
import com.rtao.observernews.base.BasePage;
import com.rtao.observernews.base.MenuDetailBasePage;
import com.rtao.observernews.domain.NewsPageBean;
import com.rtao.observernews.fragment.LeftMenuFragment;
import com.rtao.observernews.menuDetailPage.DiscuzMenuDetailPage;
import com.rtao.observernews.menuDetailPage.ImageMenuDetailPage;
import com.rtao.observernews.menuDetailPage.NewsMenuDetailPage;
import com.rtao.observernews.menuDetailPage.TopicMenuDetailPage;
import com.rtao.observernews.utils.CacheUtils;
import com.rtao.observernews.utils.Constants;
import com.rtao.observernews.utils.LogUtil;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * News page
 */
public class NewsPage extends BasePage {
    /**
     * Data for the left sliding menu
     */
    private List<NewsPageBean.DetailPageData> data;
    private List<MenuDetailBasePage> detailBasePages;

    public NewsPage(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        LogUtil.e("News page data initialized");
        ib_menu.setVisibility(View.VISIBLE);
        // 1. set title
        tv_title.setText("News");
        // 2. request data and create view
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        // 3. add this subview to the FrameLayout
        fl_content.addView(textView);
        // 4. bind data
        textView.setText("News page content");

        // retrieve cached data
        String cache = CacheUtils.getString(context, Constants.NEWS_URL);
        if(!TextUtils.isEmpty(cache)) {
            processData(cache);
        }
        // request data from Internet
        getDataFromNet();
    }

    /**
     * use xUtils3 to request data from Internet
     */
    private void getDataFromNet() {
        RequestParams source = new RequestParams(Constants.NEWS_URL);
        x.http().get(source, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                LogUtil.e("Successfully requested data from Internet using xUtils3: " +
                        result);
                // cache data
                CacheUtils.putString(context, Constants.NEWS_URL, result);
                processData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("Failed to request data from Internet using xUtils3: " +
                        ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtil.e("Cancelled requesting data from Internet using xUtils3: " +
                        cex.getMessage());
            }

            @Override
            public void onFinished() {
                LogUtil.e("Finished requesting data from Internet using xUtils3: ");
            }
        });
    }

    /**
     * parse json data and display
     * @param json
     */
    private void processData(String json) {
        NewsPageBean bean = parseJson(json);
        String title = bean.getData().get(0).getChildren().get(1).getTitle();
        LogUtil.e("Successfully parsed Json using Gson: " + title);

        data = bean.getData();
        MainActivity mainActivity = (MainActivity) context;
        LeftMenuFragment leftMenuFragment = mainActivity.getLeftMenuFragment();

        // add detail page
        detailBasePages = new ArrayList<>();
        detailBasePages.add(new NewsMenuDetailPage(context, data.get(0)));
        detailBasePages.add(new TopicMenuDetailPage(context));
        detailBasePages.add(new ImageMenuDetailPage(context));
        detailBasePages.add(new DiscuzMenuDetailPage(context));

        // pass data to the left sliding menu
        leftMenuFragment.setData(data);
    }

    private NewsPageBean parseJson(String json) {
        Gson gson = new Gson();
        NewsPageBean bean = gson.fromJson(json, NewsPageBean.class);
        return bean;
    }

    /**
     * switch detail page according to position
     * @param position
     */
    public void switchPage(int position) {
        // 1. set title
        tv_title.setText(data.get(position).getTitle());
        // 2. remove old content
        fl_content.removeAllViews();
        // 3. add new content
        MenuDetailBasePage detailBasePage = detailBasePages.get(position);
        View rootView = detailBasePage.rootView;
        detailBasePage.initData();
        fl_content.addView(rootView);
    }
}
