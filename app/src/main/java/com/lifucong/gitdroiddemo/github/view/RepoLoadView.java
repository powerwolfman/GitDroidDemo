package com.lifucong.gitdroiddemo.github.view;

import com.lifucong.gitdroiddemo.github.model.Repo;

import java.util.List;

/**
 * Created by Administrator on 2016/9/30.
 */
public interface RepoLoadView {
    void showLoadingView();
    void showLoadError();
    void showMessage(String msg);
    void addLoadData(List<Repo>repoList);
    void hideLoadView();


}
