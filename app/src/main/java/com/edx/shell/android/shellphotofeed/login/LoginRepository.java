package com.edx.shell.android.shellphotofeed.login;

public interface LoginRepository {
    void signin(String email, String pass);
    void signup(String email, String pass);
}
