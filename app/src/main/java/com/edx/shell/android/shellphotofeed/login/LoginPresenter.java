package com.edx.shell.android.shellphotofeed.login;

import com.edx.shell.android.shellphotofeed.login.events.LoginEvent;

public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void validateLogin(String email, String pass);
    void registerNewUser(String email, String pass);
    void onEventMainThread(LoginEvent loginEvent);
}
