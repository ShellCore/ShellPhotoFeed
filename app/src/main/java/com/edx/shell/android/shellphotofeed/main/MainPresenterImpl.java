package com.edx.shell.android.shellphotofeed.main;

import android.location.Location;

import com.edx.shell.android.shellphotofeed.libs.base.EventBus;
import com.edx.shell.android.shellphotofeed.main.events.MainEvent;
import com.edx.shell.android.shellphotofeed.main.ui.MainView;

import org.greenrobot.eventbus.Subscribe;

public class MainPresenterImpl implements MainPresenter {

    // Servicios
    private MainView view;
    private EventBus eventBus;
    private SessionInteractor sessionInteractor;
    private UploadInteractor uploadInteractor;

    public MainPresenterImpl(MainView view, EventBus eventBus, SessionInteractor sessionInteractor, UploadInteractor uploadInteractor) {
        this.view = view;
        this.eventBus = eventBus;
        this.sessionInteractor = sessionInteractor;
        this.uploadInteractor = uploadInteractor;
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
    public void logout() {
        sessionInteractor.logout();
    }

    @Override
    public void uploadPhoto(Location location, String path) {
        uploadInteractor.execute(location, path);
    }

    @Override
    @Subscribe
    public void onEventMainThread(MainEvent event) {
        if (view != null) {
            switch (event.getType()) {
                case MainEvent.UPLOAD_INIT:
                    view.onUploadInit();
                    break;
                case MainEvent.UPLOAD_COMPLETE:
                    view.onUploadComplete();
                    break;
                case MainEvent.UPLOAD_ERROR:
                    view.onUploadError(event.getError());
                    break;
                default:

                    break;
            }
        }
    }
}
