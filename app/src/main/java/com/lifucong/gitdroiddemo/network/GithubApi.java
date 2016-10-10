package com.lifucong.gitdroiddemo.network;


import com.lifucong.gitdroiddemo.github.hotuser.HotUserResult;
import com.lifucong.gitdroiddemo.github.model.RepoResult;
import com.lifucong.gitdroiddemo.github.repoinfo.RepoContentResult;
import com.lifucong.gitdroiddemo.login.model.AccessToken;
import com.lifucong.gitdroiddemo.login.model.User;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubApi {

    //Github 申请授权登录的信息
    public String CLIENT_ID="9511426a40d6ddab8765";
    public String CLIENT_SECRET="5632110892b5054d2bb290de2669a8ff21af58de";
    //申请填写的标志（重定向标志）
    public String CALL_BACK="lifucong";

    String AUTH_SCOPE = "user,public_repo,repo";

    // 登录页面的网址（WebView来进行访问）
    String AUTH_URL = "https://github.com/login/oauth/authorize?client_id="+CLIENT_ID+"&scope="+AUTH_SCOPE;


    /**
     * 获取仓库列表的请求Api
     *
     * @param query  查询的参数--体现为语言
     * @param pageId 查询页数--从1开始
     * @return
     */
    @GET("/search/repositories")
    Call<RepoResult> searchRepos(
            @Query("q") String query,
            @Query("page") int pageId);

    /**
     * 获取readme
     * @param owner 仓库拥有者
     * @param repo 仓库名称
     * @return
     */
    @GET("/repos/{owner}/{repo}/readme")
    Call<RepoContentResult> getReadme(@Path("owner") String owner,
                                      @Path("repo") String repo);

    /**
     * 获取MarkDown文件内容，内容以HTML形式展示出来
     * @param body
     * @return
     */
    @Headers({"Content-Type:text/plain"})
    @POST("/markdown/raw")
    Call<ResponseBody> markDown(@Body RequestBody body);

    /**
     * 获取访问令牌，根据code来获取
     *
     * @param clientId
     * @param clientSecret
     * @param code
     * @return
     */
    @FormUrlEncoded
    @POST("https://github.com/login/oauth/access_token")
    @Headers("Accept: application/json")
    Call<AccessToken> getOAuthToken(@Field("client_id")String clientId,
                                    @Field("client_secret")String clientSecret,
                                    @Field("code")String code);

    @GET("/user")
    Call<User> getUser();


    /**
     * 获取热门开发者
     * @param query  查询条件
     * @param pageId 查询页数
     * @return
     */
    @GET("/search/users")
    Call<HotUserResult> searchUsers(@Query("q")String query, @Query("page")int pageId);
}
