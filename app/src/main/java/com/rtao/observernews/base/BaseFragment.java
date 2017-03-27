package com.rtao.observernews.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Base fragment, extended by other fragments
 */
public abstract class BaseFragment extends Fragment {

    public Activity context;

    /**
     * call this function when the fragment is created
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    /**
     * call this function when the view is created
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return initView();
    }

    /**
     * subclasses should implement their own views
     * @return
     */
    public abstract View initView();

    /**
     * call this function when the activity is created
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * if data not exist, request data and bind to the view;
     * if data exist, bind directly to the view
     */
    public void initData() {}
}
