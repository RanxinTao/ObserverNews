package com.rtao.observernews.menuDetailPage;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.rtao.observernews.base.MenuDetailBasePage;
import com.rtao.observernews.utils.LogUtil;

public class ImageMenuDetailPage extends MenuDetailBasePage{

    private TextView textView;

    public ImageMenuDetailPage(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        LogUtil.e("Image detail page data initialized");
        textView.setText("Image detail page content");
    }
}
