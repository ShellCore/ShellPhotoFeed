package com.edx.shell.android.shellphotofeed.login;

public interface LoginInteractor {
    void doSignin(String email, String pass);
    void doSignup(String email, String pass);
}
