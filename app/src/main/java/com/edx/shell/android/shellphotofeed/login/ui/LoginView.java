package com.edx.shell.android.shellphotofeed.login.ui;

public interface LoginView {
    void enableInputs();
    void disableInputs();
    void showProgressbar();
    void hideProgressbar();

    void handleSignin();
    void handleSignup();

    void navigateToMainScreen();
    void loginError(String error);

    void newUserSuccess();
    void newUserError(String error);

    void setUserEmain(String email);
}
