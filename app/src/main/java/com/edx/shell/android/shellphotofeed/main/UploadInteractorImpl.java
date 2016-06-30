package com.edx.shell.android.shellphotofeed.main;

import android.location.Location;

public class UploadInteractorImpl implements UploadInteractor {

    // Servicios
    MainRepository repository;

    public UploadInteractorImpl(MainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Location location, String path) {
        repository.uploadPhoto(location, path);
    }
}
