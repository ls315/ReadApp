package com.cyhd.readapp.page.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cyhd.readapp.page.WebViewActivity;
import com.cyhd.readapp.view.DividerItemDecoration;
import com.cyhd.readapp.R;
import com.cyhd.readapp.adapter.NewsAdapter;
import com.cyhd.readapp.api.newsApi;
import com.cyhd.readapp.bean.NewsData;
import com.cyhd.readapp.http.ApiRequestFactory;

import java.util.List;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by huzhimin on 16/3/14.
 */
public class NewsDetailFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private List<NewsData> dataList;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefresh;
    private LinearLayoutManager linearLayoutManager;
    private newsApi service;
    private NewsAdapter adapter;
    private int lastVisibleItem;
    private int currentPage;
    private String type;

    public NewsDetailFragment(String type) {
        this.type = type;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news_detail, null);

        recyclerView = (RecyclerView) view.findViewById(R.id.news_detai_recycle);
        swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.news_detail_swipe);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        swipeRefresh.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
        swipeRefresh.setOnRefreshListener(this);

        service = ApiRequestFactory.getInstance().getUserApi();

        //添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        initData(action1, "0-20.html");

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {
                    initData(action3, currentPage * 20 + "-20.html");
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }
        });

        return view;
    }


    @Override
    public void onRefresh() {

        initData(action2, "0-20.html");
    }


    private void initData(Action1 action, String string) {

        service.newsData(type, string)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action, error);
    }


    //错误
    Action1<Throwable> error = new Action1<Throwable>() {
        @Override
        public void call(Throwable throwable) {
            Toast.makeText(getActivity(), throwable.toString(), Toast.LENGTH_SHORT).show();
        }
    };


    //初始加载
    Action1<Map<String, List<NewsData>>> action1 = new Action1<Map<String, List<NewsData>>>() {
        @Override
        public void call(Map<String, List<NewsData>> stringListMap) {

            currentPage++;

            swipeRefresh.setRefreshing(false);

            dataList = stringListMap.get(type);

            adapter = new NewsAdapter(dataList, getActivity());

            recyclerView.setAdapter(adapter);

            adapter.setOnItemClickLitener(new NewsAdapter.OnItemClickLitener() {
                @Override
                public void onItemClick(View view, int position) {

                    NewsData bean = dataList.get(position);
                    String url = bean.getUrl_3w();

                    if (url != null) {
                        Intent intent = new Intent(getActivity(), WebViewActivity.class);
                        intent.putExtra("url", url);
                        getActivity().startActivity(intent);
                    } else {
                        Toast.makeText(getActivity(), "url为空", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    };


    //下拉刷新
    Action1<Map<String, List<NewsData>>> action2 = new Action1<Map<String, List<NewsData>>>() {
        @Override
        public void call(Map<String, List<NewsData>> stringListMap) {
            swipeRefresh.setRefreshing(false);
            List<NewsData> list = stringListMap.get(type);
            adapter.refreshItem(list);
        }
    };


    //上拉加载
    Action1<Map<String, List<NewsData>>> action3 = new Action1<Map<String, List<NewsData>>>() {
        @Override
        public void call(Map<String, List<NewsData>> stringListMap) {
            currentPage++;
            List<NewsData> list = stringListMap.get(type);
            adapter.moreData(list);
        }
    };
}