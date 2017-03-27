package com.rtao.observernews.page;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.rtao.observernews.base.BasePage;
import com.rtao.observernews.utils.LogUtil;

/**
 * Setting page
 */
public class SettingPage extends BasePage {

    public SettingPage(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        LogUtil.e("Setting page data initialized");

        // 1. set title
        tv_title.setText("Setting");
        // 2. request data and create view
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        // 3. add this subview to the FrameLayout
        fl_content.addView(textView);
        // 4. bind data
        textView.setText("Setting page content");
    }
}
