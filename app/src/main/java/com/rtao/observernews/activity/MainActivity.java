package com.rtao.observernews.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.rtao.observernews.R;
import com.rtao.observernews.fragment.ContentFragment;
import com.rtao.observernews.fragment.LeftMenuFragment;
import com.rtao.observernews.utils.DensityUtil;

public class MainActivity extends SlidingFragmentActivity {

    public static final String MAIN_CONTENT_TAG = "main_content_tag";
    public static final String LEFTMENU_TAG = "leftmenu_tag";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE); // hide title
        super.onCreate(savedInstanceState);
        initSlidingMenu();
        initFragment();
    }

    private void initSlidingMenu() {
        // 1. set up main page
        setContentView(R.layout.activity_main);
        // 2. set up left sliding menu
        setBehindContentView(R.layout.activity_leftmenu);
        SlidingMenu slidingMenu = getSlidingMenu();
        // 3. set up right sliding menu
        //slidingMenu.setSecondaryMenu(R.layout.activity_rightmenu);
        // 4. set up displaying mode (choose from left menu + main menu, left + main + right and
        // main + right)
        slidingMenu.setMode(SlidingMenu.LEFT);
        // 5. set up sliding mode (choose from margin sliding, full screen sliding and no sliding)
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        // 6. set up the width of the main page
        slidingMenu.setBehindOffset(DensityUtil.dip2px(MainActivity.this, 200));
    }

    // initiate ContentFragment and LeftMenuFragment
    private void initFragment() {
        // 1. get FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 2. begin transaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // 3. replace
        fragmentTransaction.replace(R.id.fl_main_content, new ContentFragment(),
                MAIN_CONTENT_TAG); // main page
        fragmentTransaction.replace(R.id.fl_leftmenu, new LeftMenuFragment(),
                LEFTMENU_TAG); // main page
        // 4. commit
        fragmentTransaction.commit();
    }

    public LeftMenuFragment getLeftMenuFragment() {
        return (LeftMenuFragment) getSupportFragmentManager().findFragmentByTag(LEFTMENU_TAG);
    }

    public ContentFragment getContentFragment() {
        return (ContentFragment) getSupportFragmentManager().findFragmentByTag(MAIN_CONTENT_TAG);
    }
}
