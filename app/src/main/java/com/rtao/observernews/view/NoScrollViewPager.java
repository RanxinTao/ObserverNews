package com.rtao.observernews.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * customized ViewPager that does not support scroll
 */
public class NoScrollViewPager extends ViewPager {

    public NoScrollViewPager(Context context) { super(context); }

    public NoScrollViewPager(Context context, AttributeSet attrs) { super(context, attrs); }

    /**
     * Override onTouchEvent
     * @param ev
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) { return true; }
}
