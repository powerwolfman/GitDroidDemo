package com.lifucong.gitdroiddemo.splash;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.lifucong.gitdroiddemo.splash.pager.Pager0;
import com.lifucong.gitdroiddemo.splash.pager.Pager1;
import com.lifucong.gitdroiddemo.splash.pager.Pager2;

/**
 * Created by Administrator on 2016/9/29.
 */
public class SplashPagerAdapter extends PagerAdapter {

    //添加数据View
    private View[] views;

    public SplashPagerAdapter(Context context) {
        views = new View[]{new Pager0(context),new Pager1(context),new Pager2(context)};
    }

    //
    public View getView(int position){
        return views[position];
    }

    @Override
    public int getCount() {
        return views==null?0:views.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views[position]);
        return views[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views[position]);
    }
}

