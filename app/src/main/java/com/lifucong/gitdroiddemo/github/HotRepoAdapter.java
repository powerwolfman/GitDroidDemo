package com.lifucong.gitdroiddemo.github;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/9/29.
 */
public class HotRepoAdapter extends FragmentPagerAdapter {
    private List<Language> list;

    public HotRepoAdapter(FragmentManager fm, Context context) {
        super(fm);
        list = Language.getLanguage(context);
    }

    @Override
    public Fragment getItem(int position) {
        //将不同的语言传递到仓库列表页面
        return RepoListFragment.getInstance(list.get(position));
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getName();
    }
}
