package com.edx.shell.android.shellphotofeed.login;

public class LoginInteractorImpl implements LoginInteractor {

    private LoginRepository repository;

    public LoginInteractorImpl(LoginRepository repository) {
        this.repository = repository;
    }

    @Override
    public void doSignin(String email, String pass) {
        repository.signin(email, pass);
    }

    @Override
    public void doSignup(String email, String pass) {
        repository.signup(email, pass);
    }
}
