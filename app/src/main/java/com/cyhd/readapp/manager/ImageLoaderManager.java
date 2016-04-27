package com.cyhd.readapp.manager;

import android.content.Context;

import com.cyhd.readapp.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LRULimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * Created by huzhimin on 16/3/24.
 */
public class ImageLoaderManager {


    private static ImageLoaderManager imageLoaderManager = new ImageLoaderManager();

    private ImageLoaderManager() {

    }

    public static ImageLoaderManager getInstance() {
        return imageLoaderManager;
    }

    /**
     * 初始化ImageLoader配置
     * @param context
     */
    public void initImageLoader(Context context) {

        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(context)
                .memoryCacheExtraOptions(480, 800) //即保存的每个缓存文件的最大长宽
                .threadPriority(Thread.NORM_PRIORITY - 2) //线程池中线程的个数
                .denyCacheImageMultipleSizesInMemory() //禁止缓存多张图片
                .memoryCache(new LRULimitedMemoryCache(30 * 1024 * 1024)) //缓存策略
                .memoryCacheSize(30 * 1024 * 1024) //设置内存缓存的大小
                .diskCacheFileNameGenerator(new Md5FileNameGenerator()) //缓存文件名的保存方式
                .diskCacheSize(50 * 1024 * 1024) //磁盘缓存大小
                .tasksProcessingOrder(QueueProcessingType.LIFO) //工作队列
                .diskCacheFileCount(100) //缓存的文件数量
                .build();

        ImageLoader.getInstance().init(config);
    }


    /**
     * 初始化ImageLoader图片显示配置
     * @return
     */
    public DisplayImageOptions initImageOptions() {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.bg_image)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        return options;
    }


    /**
     * 初始化ImageLoader图片显示配置
     * @return
     */
    public DisplayImageOptions roundImageOptions() {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.bg_image)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .displayer(new RoundedBitmapDisplayer(80))
                .build();

        return options;
    }
}
