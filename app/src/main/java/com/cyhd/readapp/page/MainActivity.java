package com.cyhd.readapp.page;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.cyhd.readapp.R;
import com.cyhd.readapp.page.center.CenterFragment;
import com.cyhd.readapp.page.news.NewsFragment;
import com.cyhd.readapp.page.read.ReadFragment;
import com.cyhd.readapp.page.topic.TopicFragment;
import com.cyhd.readapp.page.video.VideoFragment;
import com.cyhd.readapp.view.BottomTabbarView;


public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";

    int currentSelection = -1;

    Fragment[] fragments = null;

    Class[] fragmentClass = {NewsFragment.class, ReadFragment.class, VideoFragment.class, TopicFragment.class, CenterFragment.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        BottomTabbarView tabbarView = (BottomTabbarView) findViewById(R.id.main_tab_bar);

        tabbarView.addTabbarItem(new BottomTabbarView.TabbarItem("新闻", R.mipmap.biz_navigation_tab_news, R.mipmap.biz_navigation_tab_news_selected));
        tabbarView.addTabbarItem(new BottomTabbarView.TabbarItem("阅读", R.mipmap.biz_navigation_tab_read, R.mipmap.biz_navigation_tab_read_selected));
        tabbarView.addTabbarItem(new BottomTabbarView.TabbarItem("视频", R.mipmap.biz_navigation_tab_va, R.mipmap.biz_navigation_tab_va_selected));
        tabbarView.addTabbarItem(new BottomTabbarView.TabbarItem("话题", R.mipmap.biz_navigation_tab_topic, R.mipmap.biz_navigation_tab_topic_selected));
        tabbarView.addTabbarItem(new BottomTabbarView.TabbarItem("我",   R.mipmap.biz_navigation_tab_pc, R.mipmap.biz_navigation_tab_pc_selected));

        fragments = new Fragment[5];

        tabbarView.setOnSelectChangeListener(new BottomTabbarView.OnSelectionChangeListener() {
            @Override
            public void onSelectionChange(int index, BottomTabbarView.TabbarItem item) {
                switchFragment(index);
            }
        });

        tabbarView.setSelectedTab(0);

    }

    private void switchFragment(int pos) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (fragments[pos] == null) {
            try {
                if (pos < fragmentClass.length) {
                    fragments[pos] = (Fragment) fragmentClass[pos].newInstance();
                    transaction.add(R.id.main_frame, fragments[pos]);
                    fragments[pos].setUserVisibleHint(true);
                }
            } catch (Exception exp) {
                Log.e(TAG, "", exp);
            }
        }

        if (currentSelection != -1 && fragments[currentSelection] != null) {
            transaction.hide(fragments[currentSelection]);
        }
        if (fragments[pos] != null) {
            transaction.show(fragments[pos]);
        }
        transaction.commit();
        currentSelection = pos;
    }
}
