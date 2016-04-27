package com.cyhd.readapp.page.topic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cyhd.readapp.R;
import com.cyhd.readapp.adapter.TopicAdapter;
import com.cyhd.readapp.api.newsApi;
import com.cyhd.readapp.bean.TopicData;
import com.cyhd.readapp.http.ApiRequestFactory;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by huzhimin on 16/3/28.
 */
public class TopicFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private static final String TAG = "TopicFragment";

    private List<TopicData.DataEntity.ExpertListEntity> dataList;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefresh;
    private LinearLayoutManager linearLayoutManager;
    private newsApi service;
    private TopicAdapter adapter;
    private int lastVisibleItem;
    private int currentPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news_detail,null);

        recyclerView = (RecyclerView) view.findViewById(R.id.news_detai_recycle);
        swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.news_detail_swipe);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        swipeRefresh.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
        swipeRefresh.setOnRefreshListener(this);

        service = ApiRequestFactory.getInstance().getUserApi();

        initData(action1,"0-10.html");

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {
                    initData(action3, currentPage * 10 + "-20.html");
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

        initData(action2,"0-10.html");

    }

    private void initData(Action1 action, String string) {

        service.topicData(string)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action, error);
    }


    //错误
    Action1<Throwable> error = new Action1<Throwable>() {
        @Override
        public void call(Throwable throwable) {
            Toast.makeText(getActivity(), throwable.toString(), Toast.LENGTH_SHORT).show();
            Log.d(TAG,throwable.toString());
        }
    };


    //初始加载
    Action1<TopicData> action1 = new Action1<TopicData>() {
        @Override
        public void call(TopicData stringListMap) {

            currentPage++;

            swipeRefresh.setRefreshing(false);

            dataList = stringListMap.getData().getExpertList();

            adapter = new TopicAdapter(dataList, getActivity());

            recyclerView.setAdapter(adapter);

//            adapter.setOnItemClickLitener(new NewsAdapter.OnItemClickLitener() {
//                @Override
//                public void onItemClick(View view, int position) {
//
//                    opicData bean = dataList.get(position);
//                    String url = bean.getUrl_3w();
//
//                    if (url != null) {
//                        Intent intent = new Intent(getActivity(), WebViewActivity.class);
//                        intent.putExtra("url", url);
//                        getActivity().startActivity(intent);
//                    } else {
//                        Toast.makeText(getActivity(), "url为空", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });


        }
    };


    //下拉刷新
    Action1<TopicData> action2 = new Action1<TopicData>() {
        @Override
        public void call(TopicData stringListMap) {
            swipeRefresh.setRefreshing(false);
            List<TopicData.DataEntity.ExpertListEntity> list = stringListMap.getData().getExpertList();
            adapter.refreshItem(list);
        }
    };


    //上拉加载
    Action1<TopicData> action3 = new Action1<TopicData>() {
        @Override
        public void call(TopicData stringListMap) {
            currentPage++;
            List<TopicData.DataEntity.ExpertListEntity> list = stringListMap.getData().getExpertList();
            adapter.moreData(list);
        }
    };
}
