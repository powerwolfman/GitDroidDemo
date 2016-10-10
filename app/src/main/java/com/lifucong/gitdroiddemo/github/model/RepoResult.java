package com.lifucong.gitdroiddemo.github.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RepoResult {

    /**
     * "total_count": 2074901,
     * "incomplete_results": false,
     * "items":[{}]
     */
    @SerializedName("total_count")
    private int totalCount;

    @SerializedName("incomplete_results")
    private boolean incompleteResults;

    @SerializedName("items")
    private List<Repo> repoList;

    public int getTotalCount() {
        return totalCount;
    }

    public boolean isIncompleteResults() {
        return incompleteResults;
    }

    public List<Repo> getRepoList() {
        return repoList;
    }
}
