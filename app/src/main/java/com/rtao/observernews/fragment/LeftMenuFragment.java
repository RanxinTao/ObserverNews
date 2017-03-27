package com.rtao.observernews.fragment;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.rtao.observernews.R;
import com.rtao.observernews.activity.MainActivity;
import com.rtao.observernews.base.BaseFragment;
import com.rtao.observernews.domain.NewsPageBean;
import com.rtao.observernews.page.NewsPage;
import com.rtao.observernews.utils.DensityUtil;
import com.rtao.observernews.utils.LogUtil;

import java.util.List;

public class LeftMenuFragment extends BaseFragment {

    private List<NewsPageBean.DetailPageData> data;
    private ListView listView;
    private LeftMenuFragmentAdapter adapter;
    private int clickPosition;

    @Override
    public View initView() {
        LogUtil.e("Left menu view initialized");
        listView = new ListView(context);
        listView.setPadding(0, DensityUtil.dip2px(context, 40), 0, 0);
        listView.setDividerHeight(0);
        listView.setCacheColorHint(Color.TRANSPARENT);
        // set the item of listView does not change color on pressed
        listView.setSelector(android.R.color.transparent);
        // set onClickEvent for the item of ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 1. record click position, make it red
                clickPosition = position;
                adapter.notifyDataSetChanged();
                // 2. close left sliding menu
                MainActivity mainActivity = (MainActivity) context;
                mainActivity.getSlidingMenu().toggle();
                // 3. switch to the related page
                switchPage(clickPosition);
            }
        });
        return listView;
    }

    private void switchPage(int position) {
        MainActivity mainActivity = (MainActivity) context;
        ContentFragment contentFragment = mainActivity.getContentFragment();
        NewsPage newsPage = contentFragment.getNewsPage();
        newsPage.switchPage(position);
    }

    @Override
    public void initData() {
        super.initData();
        LogUtil.e("Left menu data initialized");
    }

    public void setData(List<NewsPageBean.DetailPageData> data) {
        this.data = data;
        for (NewsPageBean.DetailPageData item : data) {
            LogUtil.e("Title: " + item.getTitle());
        }

        // Set adapter
        adapter = new LeftMenuFragmentAdapter();
        listView.setAdapter(adapter);

        // set default page
        switchPage(clickPosition);
    }

    class LeftMenuFragmentAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = (TextView) View.inflate(context, R.layout.item_leftmenu, null);
            textView.setText(data.get(position).getTitle());
            textView.setEnabled(position == clickPosition);
            return textView;
        }
    }
}
