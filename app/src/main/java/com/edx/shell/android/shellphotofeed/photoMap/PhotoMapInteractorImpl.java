package com.edx.shell.android.shellphotofeed.photoMap;

public class PhotoMapInteractorImpl implements PhotoMapInteractor {

    // Servicios
    private PhotoMapRepository repository;

    public PhotoMapInteractorImpl(PhotoMapRepository repository) {
        this.repository = repository;
    }

    @Override
    public void subscribe() {
        repository.subscribe();
    }

    @Override
    public void unsbuscribe() {
        repository.unsubscribe();
    }
}
