package com.cyhd.readapp.page.read;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cyhd.readapp.R;
import com.cyhd.readapp.adapter.ReadAdapter;
import com.cyhd.readapp.adapter.NewsAdapter;
import com.cyhd.readapp.api.newsApi;
import com.cyhd.readapp.bean.ReadData;
import com.cyhd.readapp.bean.ReadDetailData;
import com.cyhd.readapp.http.ApiRequestFactory;
import com.cyhd.readapp.page.DetailActivity;
import com.cyhd.readapp.page.WebViewActivity;

import java.util.List;
import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by huzhimin on 16/3/28.
 */
public class ReadFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private List<ReadData> dataList;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefresh;
    private LinearLayoutManager linearLayoutManager;
    private newsApi service;
    private ReadAdapter adapter;
    private int lastVisibleItem;
    private int currentPage;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_read_detail, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.read_detai_recycle);
        swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.read_detail_swipe);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        swipeRefresh.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
        swipeRefresh.setOnRefreshListener(this);

        service = ApiRequestFactory.getInstance().getUserApi();

        initData(action1, "getSubDocPic?from=yuedu&size=20&passport=&devId=8dT84i5cIeVj9GQ2FULynIkR%2BJFmCTKeVEVQluPMzMTRkyGDk5NaBUe0lMqPw1jD&lat=&lon=&version=5.6.2&net=wifi&ts=1459222178&sign=AnibxY86rEAG7ulCq23137jouX4RGv4Dzfm4X5yIaUN48ErR02zJ6%2FKXOnxX046I&encryption=1&canal=netease_gw");

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {
                    initData(action3, "getSubDocNews?from=yuedu&size=20&passport=&devId=8dT84i5cIeVj9GQ2FULynIkR%2BJFmCTKeVEVQluPMzMTRkyGDk5NaBUe0lMqPw1jD&lat=adYQMOgkAsWnjG28%2Fwz0kA%3D%3D&lon=LdnCho%2FqHaIQ7S9dUd876A%3D%3D&version=5.6.2&net=wifi&ts=1459248617&sign=vSBlciqItETqe95iOK9phOEnkNGZY%2FhYjLDVXtQvu7R48ErR02zJ6%2FKXOnxX046I&encryption=1&canal=netease_gw");
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

        initData(action2, "getSubDocPic?from=yuedu&size=20&passport=&devId=8dT84i5cIeVj9GQ2FULynIkR%2BJFmCTKeVEVQluPMzMTRkyGDk5NaBUe0lMqPw1jD&lat=&lon=&version=5.6.2&net=wifi&ts=1459222178&sign=AnibxY86rEAG7ulCq23137jouX4RGv4Dzfm4X5yIaUN48ErR02zJ6%2FKXOnxX046I&encryption=1&canal=netease_gw");

    }

    private void initData(Action1 action, String string) {

        service.readData(string)
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
    Action1<Map<String, List<ReadData>>> action1 = new Action1<Map<String, List<ReadData>>>() {
        @Override
        public void call(final Map<String, List<ReadData>> stringListMap) {

            currentPage++;

            swipeRefresh.setRefreshing(false);

            dataList = stringListMap.get("推荐");

            adapter = new ReadAdapter(dataList, getActivity());

            recyclerView.setAdapter(adapter);

            adapter.setOnItemClickLitener(new ReadAdapter.OnItemClickLitener() {
                @Override
                public void onItemClick(View view, int position) {

                    ReadData bean = dataList.get(position);
                    final String id = bean.getId();
                    service.detailData(id)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<Map<String, ReadDetailData>>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onNext(Map<String, ReadDetailData> stringListMap) {

                                    ReadDetailData data = stringListMap.get(id);

                                    Intent intent = new Intent(getActivity(), DetailActivity.class);

                                    intent.putExtra("ReadDetailData", data);

                                    startActivity(intent);

                                }
                            });

                }
            });
        }
    };


    //下拉刷新
    Action1<Map<String, List<ReadData>>> action2 = new Action1<Map<String, List<ReadData>>>() {
        @Override
        public void call(Map<String, List<ReadData>> stringListMap) {
            swipeRefresh.setRefreshing(false);
            List<ReadData> list = stringListMap.get("推荐");
            adapter.refreshItem(list);
        }
    };


    //上拉加载
    Action1<Map<String, List<ReadData>>> action3 = new Action1<Map<String, List<ReadData>>>() {
        @Override
        public void call(Map<String, List<ReadData>> stringListMap) {
            currentPage++;
            List<ReadData> list = stringListMap.get("推荐");
            adapter.moreData(list);
        }
    };
}
