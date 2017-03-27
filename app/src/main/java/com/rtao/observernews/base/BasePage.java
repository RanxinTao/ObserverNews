package com.rtao.observernews.base;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.rtao.observernews.R;
import com.rtao.observernews.activity.MainActivity;

public class BasePage {

    public final Context context;
    public View rootView;
    public TextView tv_title;
    public ImageButton ib_menu; // to open the sliding menu
    public FrameLayout fl_content; // subclass view container

    public BasePage(Context context) {
        this.context = context;
        rootView = initView();
    }

    /**
     * initiate common part of the view, and the FrameLayout for the different views of subclasses
     * @return
     */
    private View initView() {
        // the page of base class
        View view = View.inflate(context, R.layout.base_page, null);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        ib_menu = (ImageButton) view.findViewById(R.id.ib_menu);
        fl_content = (FrameLayout) view.findViewById(R.id.fl_content);
        ib_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) context;
                mainActivity.getSlidingMenu().toggle();
            }
        });
        return view;
    }

    /**
     * Initiate data. Override this method if subclasses need to request data and bind data.
     */
    public void initData() {}
}
