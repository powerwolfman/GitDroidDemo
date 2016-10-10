package com.lifucong.gitdroiddemo.login;

import android.util.Log;

import com.lifucong.gitdroiddemo.login.model.AccessToken;
import com.lifucong.gitdroiddemo.login.model.User;
import com.lifucong.gitdroiddemo.login.model.UserRepo;
import com.lifucong.gitdroiddemo.network.GithubApi;
import com.lifucong.gitdroiddemo.network.GithubClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/10/8.
 */

public class LoginPresenter {

    private Call<AccessToken> token;
    private Call<User> userCall;
    private LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    /**
     * 登录的业务
     * 1、使用code  来获取Token
     * 2、使用Token 来获取用户信息User
     * @param code
     */
    public void login(String code){
        loginView.showProgress();
        if (token!=null) {
            token.cancel();
        }
        //利用code去获取Token
        token = GithubClient.getInstance().
                getOAuthToken(GithubApi.CLIENT_ID, GithubApi.CLIENT_SECRET, code);
        token.enqueue(tokenCallBacK);
    }

    private Callback<AccessToken> tokenCallBacK=new Callback<AccessToken>() {
        private Call<User> userCall;
        @Override
        public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
            if (response.isSuccessful()) {
                //得到Token值
                AccessToken accessToken = response.body();
                String token = accessToken.getAccessToken();

                UserRepo.setAccessToken(token);

                if (userCall!=null) {
                    userCall.cancel();
                }
                userCall = GithubClient.getInstance().getUser();
                userCall.enqueue(userCallBack);
            }
        }

        //请求失败
        @Override
        public void onFailure(Call<AccessToken> call, Throwable t) {
            loginView.showMessage(t.getMessage());
            loginView.resetWebView();//重新去请求
            loginView.showProgress();
        }
    };

    private Callback<User>userCallBack=new Callback<User>() {
        @Override
        public void onResponse(Call<User> call, Response<User> response) {
            if (response.isSuccessful()) {
                //将获取的用户信息保存
                User user = response.body();
                UserRepo.setUser(user);
                loginView.showMessage("登录成功");
                loginView.navigationToMain();
            }
        }

        //请求失败
        @Override
        public void onFailure(Call<User> call, Throwable t) {
            loginView.showMessage(t.getMessage());
            loginView.resetWebView();//重新去请求
            loginView.showProgress();
        }
    };
}
