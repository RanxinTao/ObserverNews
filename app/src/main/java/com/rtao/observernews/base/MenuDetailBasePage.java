package com.rtao.observernews.base;

import android.content.Context;
import android.view.View;

public abstract class MenuDetailBasePage {

    public final Context context;
    public View rootView;

    public MenuDetailBasePage(Context context) {
        this.context = context;
        rootView = initView();
    }

    /**
     * Each page has its own view and they do not have common part here
     * @return
     */
    public abstract View initView();

    /**
     * override this method if subclass needs to request data and bind data
     */
    public void initData() {}
}
