package com.edx.shell.android.shellphotofeed.photoMap.di;

import com.edx.shell.android.shellphotofeed.domain.FirebaseAPI;
import com.edx.shell.android.shellphotofeed.libs.base.EventBus;
import com.edx.shell.android.shellphotofeed.photoMap.PhotoMapInteractor;
import com.edx.shell.android.shellphotofeed.photoMap.PhotoMapInteractorImpl;
import com.edx.shell.android.shellphotofeed.photoMap.PhotoMapPresenter;
import com.edx.shell.android.shellphotofeed.photoMap.PhotoMapPresenterImpl;
import com.edx.shell.android.shellphotofeed.photoMap.PhotoMapRepository;
import com.edx.shell.android.shellphotofeed.photoMap.PhotoMapRepositoryImpl;
import com.edx.shell.android.shellphotofeed.photoMap.ui.PhotoMapView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PhotoMapModule {

    // Servicios
    private PhotoMapView view;

    public PhotoMapModule(PhotoMapView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    PhotoMapView providesPhotoMapView() {
        return view;
    }

    @Provides @Singleton
    PhotoMapPresenter providesPhotoMapPresenter(EventBus eventBus, PhotoMapView view, PhotoMapInteractor interactor) {
        return new PhotoMapPresenterImpl(eventBus, view, interactor);
    }

    @Provides @Singleton
    PhotoMapInteractor providesPhotoMapInteractor(PhotoMapRepository repository) {
        return new PhotoMapInteractorImpl(repository);
    }

    @Provides @Singleton
    PhotoMapRepository providesPhotoMapRepository(EventBus eventBus, FirebaseAPI api) {
        return new PhotoMapRepositoryImpl(eventBus, api);
    }
}
