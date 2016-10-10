package com.lifucong.gitdroiddemo.github.view;

import com.lifucong.gitdroiddemo.github.model.Repo;
import com.lifucong.gitdroiddemo.github.model.RepoResult;

import java.util.List;

/**
 * Created by Administrator on 2016/9/30.
 */
public interface RepoRefreshView {
    void showContentView();
    void stopRefresh();
    void refreshData(List<Repo>repoList);
    void showEmptyView();
    void showMessage(String msg);
    void showErrorView();
}
