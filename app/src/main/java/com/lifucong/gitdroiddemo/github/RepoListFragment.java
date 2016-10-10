package com.lifucong.gitdroiddemo.github;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.lifucong.gitdroiddemo.R;
import com.lifucong.gitdroiddemo.commons.ActivityUtils;
import com.lifucong.gitdroiddemo.components.FooterView;
import com.lifucong.gitdroiddemo.github.model.Repo;
import com.lifucong.gitdroiddemo.github.repoinfo.RepoInfoActivity;
import com.lifucong.gitdroiddemo.github.view.RepoListAllView;
import com.mugen.Mugen;
import com.mugen.MugenCallbacks;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 * Created by Administrator on 2016/9/29.
 */

public class RepoListFragment extends Fragment implements RepoListAllView {


    private static final String KEY_LANGUAGE = "key_language";
    @BindView(R.id.lvRepos)
    ListView lvRepos;
    @BindView(R.id.ptrClassicFrameLayout)
    PtrClassicFrameLayout ptrFrameLayout;
    @BindView(R.id.emptyView)
    TextView emptyView;
    @BindView(R.id.errorView)
    TextView errorView;
    private ActivityUtils activityUtils;
    private RepoListAdapter adapter;
    private RepoListPresenter presenter;
    private FooterView footerView;

    //创建一个方法进行数据（language）的传值
    public static RepoListFragment getInstance(Language language) {
        RepoListFragment repoListFragment = new RepoListFragment();
        //bundle进行数据的传递
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_LANGUAGE, language);
        repoListFragment.setArguments(bundle);
        return repoListFragment;
    }

    private Language getLanguage() {
        return (Language) getArguments().getSerializable(KEY_LANGUAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repo_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activityUtils = new ActivityUtils(this);
        presenter = new RepoListPresenter(this, getLanguage());
        adapter = new RepoListAdapter();
        lvRepos.setAdapter(adapter);

        lvRepos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Repo repo = adapter.getItem(position);
                RepoInfoActivity.open(getContext(), repo);
            }
        });
        //判断没有数据就自动刷新
        if (adapter.getCount() == 0) {
            ptrFrameLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //自动刷新的方法
                    ptrFrameLayout.autoRefresh();
                }
            }, 200);
        }

        //初始化下拉刷新
        initPullToFresh();

        //上拉加载的事件
        /**
         * Listview滑动到最后一条时，再进行滑动，触发加载
         * Listview有一个尾部布局，显示正在加载视图
         * 加载完成后，尾部布局移除
         */
        initLoadMoreData();
    }

    private void initLoadMoreData() {
        footerView = new FooterView(getContext());

        Mugen.with(lvRepos, new MugenCallbacks() {

            //执行加载，上拉加载业务的请求
            @Override
            public void onLoadMore() {
                presenter.loadMoreData();
            }

            //是不是正在加载
            @Override
            public boolean isLoading() {
                return footerView.isLoading() && lvRepos.getFooterViewsCount() > 0;
            }

            //是不是加载完了所有的数据
            @Override
            public boolean hasLoadedAllItems() {
                return footerView.isComplete() && lvRepos.getFooterViewsCount() > 0;
            }
        }).start();

    }

    private void initPullToFresh() {
        //刷新时间间隔较短时，不触发刷新
        ptrFrameLayout.setLastUpdateTimeRelateObject(this);

        //关闭header所用时间
        ptrFrameLayout.setDurationToClose(1500);

        //更改头布局
        StoreHouseHeader header = new StoreHouseHeader(getContext());
        header.initWithString("I LIKE ANDROID");
        header.setPadding(0, 60, 0, 60);
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.addPtrUIHandler(header);
        ptrFrameLayout.setBackgroundResource(R.color.colorRefresh);
        //设置刷新的监听，做刷新的操作（去进行数据请求）
        ptrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                // 去做数据的请求
                presenter.refresh();
            }
        });
    }

    //---------------------刷新的视图----------------------------
    @Override
    public void stopRefresh() {
        ptrFrameLayout.refreshComplete();//停止刷新
    }

    @Override
    public void showMessage(String msg) {
        activityUtils.showToast(msg);
    }

    @Override
    public void refreshData(List<Repo> repoList) {
        adapter.clear();
        adapter.addAll(repoList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showEmptyView() {
        ptrFrameLayout.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
    }


    @Override
    public void showContentView() {
        ptrFrameLayout.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
    }


    @Override
    public void showErrorView() {
        ptrFrameLayout.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
    }


    //--------------------加载的视图--------------------------
    @Override
    public void showLoadingView() {
        if (lvRepos.getFooterViewsCount() == 0) {
            lvRepos.addFooterView(footerView);
        }
        footerView.showLoading();
    }

    @Override
    public void hideLoadView() {
        lvRepos.removeFooterView(footerView);
    }

    @Override
    public void showLoadError() {
        if (lvRepos.getFooterViewsCount() == 0) {
            lvRepos.addFooterView(footerView);
        }
        footerView.showError();
    }

    @Override
    public void addLoadData(List<Repo> repoList) {
        adapter.addAll(repoList);
        adapter.notifyDataSetChanged();
    }
}
