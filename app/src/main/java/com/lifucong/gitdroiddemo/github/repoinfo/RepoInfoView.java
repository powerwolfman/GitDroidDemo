package com.lifucong.gitdroiddemo.github.repoinfo;

/**
 * Created by 123 on 2016/9/30.
 */
public interface RepoInfoView {

    /**
     * 1.显示进度条
     * 2.隐藏进度条
     * 3.显示信息
     * 4.加载完数据，显示数据
     */

    void showProgress();

    void hideProgress();

    void showMessage(String msg);

    void setData(String htmlContent);

}
