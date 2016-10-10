package com.lifucong.gitdroiddemo.splash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lifucong.gitdroiddemo.MainActivity;
import com.lifucong.gitdroiddemo.R;
import com.lifucong.gitdroiddemo.commons.ActivityUtils;
import com.lifucong.gitdroiddemo.login.LoginActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends AppCompatActivity {

    private ActivityUtils activityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        activityUtils = new ActivityUtils(this);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btnLogin)
    void login() {
        activityUtils.startActivity(LoginActivity.class);
        finish();
    }

    @OnClick(R.id.btnEnter)
    void enter() {
        activityUtils.startActivity(MainActivity.class);
        finish();
    }
}
