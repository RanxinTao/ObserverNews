package com.rtao.observernews.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.rtao.observernews.R;
import com.rtao.observernews.activity.MainActivity;
import com.rtao.observernews.adapter.ContentFragmentAdapter;
import com.rtao.observernews.base.BaseFragment;
import com.rtao.observernews.base.BasePage;
import com.rtao.observernews.page.GOVPage;
import com.rtao.observernews.page.HomePage;
import com.rtao.observernews.page.NewsPage;
import com.rtao.observernews.page.ServicePage;
import com.rtao.observernews.page.SettingPage;
import com.rtao.observernews.utils.LogUtil;
import com.rtao.observernews.view.NoScrollViewPager;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class ContentFragment extends BaseFragment {

    @ViewInject(R.id.viewpager) // viewPager = (ViewPager) view.findViewById(R.id.viewpager);
    private NoScrollViewPager viewPager;
    @ViewInject(R.id.rg_main) // rg_main = (RadioGroup) view.findViewById(R.id.rg_main);
    private RadioGroup rg_main;
    private List<BasePage> basePages;

    @Override
    public View initView() {
        LogUtil.e("Content view initialized");
        View view = View.inflate(context, R.layout.content_fragment, null);

        // 1. inject view into the framework: associate ContentFragment to the view
        x.view().inject(ContentFragment.this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        LogUtil.e("Content data initialized");

        // initiate five subpages (Home, News, Service, GOV and Setting)
        basePages = new ArrayList<>();
        basePages.add(new HomePage(context));
        basePages.add(new NewsPage(context));
        basePages.add(new ServicePage(context));
        basePages.add(new GOVPage(context));
        basePages.add(new SettingPage(context));

        // Set ViewPager adapter
        viewPager.setAdapter(new ContentFragmentAdapter(basePages));

        rg_main.setOnCheckedChangeListener(new BottomTabOnCheckedChangeListener());

        // Listener that monitors if the page is being selected
        viewPager.addOnPageChangeListener(new OnPageChangeListener());
        // Set default content page. Assign HomePage to the FrameLayout by default
        rg_main.check(R.id.rb_home);
        // Initialize data for the HomePage
        basePages.get(0).initData();
        // Disable SlidingMenu in the HomePage
        enableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
    }

    public NewsPage getNewsPage() {
        return (NewsPage) basePages.get(1);
    }

    class OnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        /**
         * call this function when the page is being selected
         * @param position
         */
        @Override
        public void onPageSelected(int position) {
            // avoid preload
            basePages.get(position).initData();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    class BottomTabOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        /**
         *
         * @param group RadioGroup
         * @param checkedId id of the checked radio bottom
         */
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_home:
                    viewPager.setCurrentItem(0, false);
                    enableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.rb_newscenter:
                    viewPager.setCurrentItem(1, false);
                    enableSlidingMenu(SlidingMenu.TOUCHMODE_FULLSCREEN);
                    break;
                case R.id.rb_smartservice:
                    viewPager.setCurrentItem(2, false);
                    enableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.rb_govaffair:
                    viewPager.setCurrentItem(3, false);
                    enableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.rb_setting:
                    viewPager.setCurrentItem(4, false);
                    enableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
            }
        }
    }

    /**
     * set if enables SlidingMenu based on the pass-in parameter
     * @param parameter
     */
    private void enableSlidingMenu(int parameter) {
        MainActivity mainActivity = (MainActivity) context;
        mainActivity.getSlidingMenu().setTouchModeAbove(parameter);
    }
}
