package com.cyhd.readapp.api;


import com.cyhd.readapp.bean.ReadDetailData;
import com.cyhd.readapp.bean.TopicData;
import com.cyhd.readapp.bean.NewsData;
import com.cyhd.readapp.bean.ReadData;
import com.cyhd.readapp.bean.VideoData;

import java.util.List;
import java.util.Map;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by huzhimin on 16/3/15.
 */
public interface newsApi {


    @GET("/nc/article/list/{type}/{number}")
    Observable<Map<String, List<NewsData>>> newsData(@Path("type") String type, @Path("number") String number);


    @GET("/recommend/{string}")
    Observable<Map<String, List<ReadData>>> readData(@Path("string") String string);


    @GET("/newstopic/list/expert/{number}")
    Observable<TopicData> topicData(@Path("number") String number);


    @GET("/nc/video/Tlist/{type}/{number}")
    Observable<Map<String, List<VideoData>>> videoData(@Path("type") String type, @Path("number") String number);


    @GET("/nc/article/{type}/full.html")
    Observable<Map<String, ReadDetailData>> detailData(@Path("type") String type);


}
