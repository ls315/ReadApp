package com.cyhd.readapp.http;

import com.cyhd.readapp.api.newsApi;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by huzhimin on 16/3/17.
 */
public class ApiRequestFactory {

    private Retrofit retrofit;

    static ApiRequestFactory requestFactory = null;

    public static ApiRequestFactory getInstance(){
        if (requestFactory == null){
            synchronized (ApiRequestFactory.class){
                if (requestFactory == null){
                    requestFactory = new ApiRequestFactory();
                }
            }
        }
        return requestFactory;
    }


    private ApiRequestFactory(){

        retrofit = new Retrofit.Builder()
                .baseUrl("http://c.3g.163.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public newsApi getUserApi(){
        newsApi service = retrofit.create(newsApi.class);

        return service;
    }
}
