package com.rtao.observernews.menuDetailPage;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.rtao.observernews.R;
import com.rtao.observernews.activity.MainActivity;
import com.rtao.observernews.base.MenuDetailBasePage;
import com.rtao.observernews.domain.NewsPageBean;
import com.rtao.observernews.menuDetailPage.tabDetailPage.TabDetailPage;
import com.rtao.observernews.utils.LogUtil;
import com.viewpagerindicator.TabPageIndicator;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class NewsMenuDetailPage extends MenuDetailBasePage{

    @ViewInject(R.id.tabPageIndicator)
    private TabPageIndicator tabPageIndicator;
    @ViewInject(R.id.viewpager)
    private ViewPager viewPager;
    @ViewInject(R.id.ib_tab_next)
    private ImageButton ib_tab_next;

    private List<NewsPageBean.DetailPageData.ChildrenData> children;
    private List<TabDetailPage> tabDetailPages;

    public NewsMenuDetailPage(Context context, NewsPageBean.DetailPageData detailPageData) {
        super(context);
        children = detailPageData.getChildren();
    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.news_menu_detail_page, null);
        x.view().inject(NewsMenuDetailPage.this, view);
        ib_tab_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        });
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        LogUtil.e("News detail page data initialized");

        tabDetailPages = new ArrayList<>();
        for (NewsPageBean.DetailPageData.ChildrenData item : children) {
            tabDetailPages.add(new TabDetailPage(context, item));
        }

        // set viewPager adapter
        viewPager.setAdapter(new NewsMenuDetailPageAdapter());
        // associate ViewPager to TabPageIndicator
        tabPageIndicator.setViewPager(viewPager);

        tabPageIndicator.setOnPageChangeListener(new OnPageChangeListener());
    }

    class OnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == 0) {
                // enable sliding menu
                enableSlidingMenu(SlidingMenu.TOUCHMODE_FULLSCREEN);
            } else {
                // disable sliding menu
                enableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private void enableSlidingMenu(int parameter) {
        MainActivity mainActivity = (MainActivity) context;
        mainActivity.getSlidingMenu().setTouchModeAbove(parameter);
    }

    class NewsMenuDetailPageAdapter extends PagerAdapter {

        @Override
        public CharSequence getPageTitle(int position) {
            return children.get(position).getTitle();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TabDetailPage tabDetailPage = tabDetailPages.get(position);
            View rootView = tabDetailPage.rootView;
            tabDetailPage.initData();
            container.addView(rootView);
            return rootView;
        }

        @Override
        public int getCount() {
            return tabDetailPages.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
