package com.edx.shell.android.shellphotofeed.photoList;

import com.edx.shell.android.shellphotofeed.entities.Photo;
import com.edx.shell.android.shellphotofeed.libs.base.EventBus;
import com.edx.shell.android.shellphotofeed.photoList.events.PhotoListEvent;
import com.edx.shell.android.shellphotofeed.photoList.ui.PhotoListView;

import org.greenrobot.eventbus.Subscribe;

public class PhotoListPresenterImpl implements PhotoListPresenter {

    // Constantes
    private static final String EMPTY_STRING = "Listado vacío";

    // Servicios
    private EventBus eventBus;
    private PhotoListView view;
    private PhotoListInteractor interactor;

    public PhotoListPresenterImpl(EventBus eventBus, PhotoListView view, PhotoListInteractor interactor) {
        this.eventBus = eventBus;
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        view = null;
        eventBus.unregister(this);
    }

    @Override
    public void subscribe() {
        if (view != null) {
            view.hideList();
            view.showProgress();
        }
        interactor.subscribe();
    }

    @Override
    public void unsubscribe() {
        interactor.unsubscribe();
    }

    @Override
    public void removePhoto(Photo photo) {
        interactor.removePhoto(photo);
    }

    @Override
    @Subscribe
    public void onEventMainThread(PhotoListEvent event) {
        if (view != null) {
            view.hideProgress();
            view.showList();
        }
        String error = event.getError();
        if (error != null) {
            if (error.isEmpty()) {
                view.onPhotoError(EMPTY_STRING);
            } else {
                view.onPhotoError(error);
            }
        } else {
            switch (event.getType()) {
                case PhotoListEvent.READ_EVENT:
                    view.addPhoto(event.getPhoto());
                    break;
                case PhotoListEvent.DELETE_EVENT:
                    view.removePhoto(event.getPhoto());
                    break;
                default:
                    break;
            }
        }
    }
}
