package com.edx.shell.android.shellphotofeed.photoMap;

import com.edx.shell.android.shellphotofeed.libs.base.EventBus;
import com.edx.shell.android.shellphotofeed.photoMap.events.PhotoMapEvent;
import com.edx.shell.android.shellphotofeed.photoMap.ui.PhotoMapView;

import org.greenrobot.eventbus.Subscribe;

public class PhotoMapPresenterImpl implements PhotoMapPresenter {

    // Servicios
    private EventBus eventBus;
    private PhotoMapView view;
    private PhotoMapInteractor interactor;

    public PhotoMapPresenterImpl(EventBus eventBus, PhotoMapView view, PhotoMapInteractor interactor) {
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
        interactor.subscribe();
    }

    @Override
    public void unsubscribe() {
        interactor.unsbuscribe();
    }

    @Override
    @Subscribe
    public void onEventMainThread(PhotoMapEvent event) {
        if (view != null) {
            if (event.getError() != null) {
                view.onPhotoError(event.getError());
            } else {
                switch (event.getType()) {
                    case PhotoMapEvent.READ_EVENT:
                        view.addPhoto(event.getPhoto());
                        break;
                    case PhotoMapEvent.DELETE_EVENT:
                        view.removePhoto(event.getPhoto());
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
