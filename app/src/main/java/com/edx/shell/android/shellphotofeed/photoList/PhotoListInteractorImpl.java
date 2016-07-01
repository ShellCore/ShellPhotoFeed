package com.edx.shell.android.shellphotofeed.photoList;

import com.edx.shell.android.shellphotofeed.entities.Photo;

public class PhotoListInteractorImpl implements PhotoListInteractor {

    // Servicios
    private PhotoListRepository repository;

    public PhotoListInteractorImpl(PhotoListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void subscribe() {
        repository.subscribe();
    }

    @Override
    public void unsubscribe() {
        repository.unsubscribe();
    }

    @Override
    public void removePhoto(Photo photo) {
        repository.removePhoto(photo);
    }
}
