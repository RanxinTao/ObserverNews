package com.rtao.observernews.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.rtao.observernews.base.BasePage;

import java.util.List;

public class ContentFragmentAdapter extends PagerAdapter {

    private final List<BasePage> basePages;

    public ContentFragmentAdapter(List<BasePage> basePages) {
        this.basePages = basePages;
    }

    @Override
    public int getCount() {
        return basePages.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        BasePage basePage = basePages.get(position);
        View rootView = basePage.rootView; // subviews
        // this method does preload (request data from Internet in advance), which should be disabled
        //basePage.initData();
        container.addView(rootView);
        return rootView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
