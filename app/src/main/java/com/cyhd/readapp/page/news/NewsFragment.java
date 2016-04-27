package com.cyhd.readapp.page.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cyhd.readapp.R;
import com.cyhd.readapp.adapter.fragmentViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huzhimin on 16/3/28.
 */
public class NewsFragment extends Fragment{

    private TabLayout mTab;
    private List<Fragment> fragmentList;
    private List<String> titleList;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_news,null);
        initViews();
        return view;
    }


    private void initViews() {

        ViewPager mPager = (ViewPager) view.findViewById(R.id.news_content_pager);
        mTab = (TabLayout) view.findViewById(R.id.news_tab_bar);

        //设置TabLayout的模式
        mTab.setTabMode(TabLayout.MODE_FIXED);

        initData();

        fragmentViewPagerAdapter adapter = new fragmentViewPagerAdapter(getFragmentManager(), fragmentList, titleList);

        mPager.setOffscreenPageLimit(5);

        //viewpager加载adapter
        mPager.setAdapter(adapter);

        mTab.setupWithViewPager(mPager);

    }


    private void initData() {

        fragmentList = new ArrayList<>();

        String[] type = {"T1348648517839", "T1348649079062", "T1348649580692", "T1368497029546"};

        for (int i = 0; i < type.length; i++) {
            NewsDetailFragment fragment = new NewsDetailFragment(type[i]);
            fragmentList.add(fragment);
        }

        String[] title = {"娱乐", "体育", "科技", "历史"};

        titleList = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            titleList.add(title[i]);
        }

        //为TabLayout添加tab名称
        for (int i = 0; i < titleList.size(); i++) {
            mTab.addTab(mTab.newTab().setText(titleList.get(i)));
        }
    }
}
