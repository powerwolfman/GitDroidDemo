package com.lifucong.gitdroiddemo;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by 123 on 2016/9/29.
 */
public class GitApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();


        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.ic_avatar)
                .showImageOnFail(R.drawable.ic_avatar)
                .cacheInMemory(true)//打开内存缓存
                .cacheOnDisk(true)//打开硬盘缓存
                .build();

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheSize(1024 * 1024 * 10)
                .defaultDisplayImageOptions(options)
                .build();

        ImageLoader.getInstance().init(configuration);

    }
}
