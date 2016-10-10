package com.lifucong.gitdroiddemo.github.model;


import com.google.gson.annotations.SerializedName;
import com.lifucong.gitdroiddemo.login.model.User;

import java.io.Serializable;

public class Repo implements Serializable{
    /**
     * {
     "id": 29028775,
     "name": "react-native",
     "full_name": "facebook/react-native",
     "owner": {
     "login": "facebook",
     "id": 69631,
     "avatar_url": "https://avatars.githubusercontent.com/u/69631?v=3",
     },
     "description": "A framework for building native apps with React.",
     "stargazers_count": 33961,
     "forks_count": 7122,
     }
     */

    private int id;
    private String name;

    @SerializedName("full_name")
    private String fullName;

    private String description;

    @SerializedName("stargazers_count")
    private int starCount;

    @SerializedName("forks_count")
    private int forksCount;

    private User owner;

    public User getOwner() {
        return owner;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDescription() {
        return description;
    }

    public int getStarCount() {
        return starCount;
    }

    public int getForksCount() {
        return forksCount;
    }
}
