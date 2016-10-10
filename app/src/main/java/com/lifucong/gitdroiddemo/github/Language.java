package com.lifucong.gitdroiddemo.github;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/9/29.
 */
public class Language implements Serializable{

    /**
     * path : java
     * name : Java
     */

    private String path;
    private String name;

    private static List<Language> languages;

    //对本地的json字符串文件进行读取，通过Gson解析
    public static List<Language> getLanguage(Context context) {
        if (languages != null) {
            return languages;
        }
        try {
            InputStream inputStream = context.getAssets().open("langs.json");
            String content = IOUtils.toString(inputStream);
            Gson gson = new Gson();
            languages = gson.fromJson(content, new TypeToken<List<Language>>() {
            }.getType());
            return languages;
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
