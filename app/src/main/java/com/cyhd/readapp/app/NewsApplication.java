package com.cyhd.readapp.app;

import android.app.Application;
import android.util.Log;

import com.cyhd.readapp.manager.ImageLoaderManager;

/**
 * Created by huzhimin on 16/3/24.
 */
public class NewsApplication extends Application{

    private static final String TAG = "NewsApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "NewsApplication is onCreate");

        ImageLoaderManager.getInstance().initImageLoader(this);

    }
}
