package com.edx.shell.android.shellphotofeed.main;

public class SessionInteractorImpl implements SessionInteractor {

    // Servicios
    private MainRepository repository;

    public SessionInteractorImpl(MainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void logout() {
        repository.logout();
    }
}
