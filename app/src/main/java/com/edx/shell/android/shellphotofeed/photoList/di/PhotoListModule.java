package com.edx.shell.android.shellphotofeed.photoList.di;

import com.edx.shell.android.shellphotofeed.domain.FirebaseAPI;
import com.edx.shell.android.shellphotofeed.domain.Util;
import com.edx.shell.android.shellphotofeed.entities.Photo;
import com.edx.shell.android.shellphotofeed.libs.base.EventBus;
import com.edx.shell.android.shellphotofeed.libs.base.ImageLoader;
import com.edx.shell.android.shellphotofeed.photoList.PhotoListInteractor;
import com.edx.shell.android.shellphotofeed.photoList.PhotoListInteractorImpl;
import com.edx.shell.android.shellphotofeed.photoList.PhotoListPresenter;
import com.edx.shell.android.shellphotofeed.photoList.PhotoListPresenterImpl;
import com.edx.shell.android.shellphotofeed.photoList.PhotoListRepository;
import com.edx.shell.android.shellphotofeed.photoList.PhotoListRepositoryImpl;
import com.edx.shell.android.shellphotofeed.photoList.adapters.OnItemClickListener;
import com.edx.shell.android.shellphotofeed.photoList.adapters.PhotoListAdapter;
import com.edx.shell.android.shellphotofeed.photoList.ui.PhotoListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PhotoListModule {

    // Variables
    private PhotoListView view;
    private OnItemClickListener clickListener;

    public PhotoListModule(PhotoListView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    PhotoListView providesPhotoListView() {
        return view;
    }

    @Provides @Singleton
    PhotoListPresenter providesPhotoListPresenter(EventBus eventBus, PhotoListView view, PhotoListInteractor interactor) {
        return new PhotoListPresenterImpl(eventBus, view, interactor);
    }

    @Provides @Singleton
    PhotoListInteractor providesPhotoListInteractor(PhotoListRepository repository) {
        return new PhotoListInteractorImpl(repository);
    }

    @Provides @Singleton
    PhotoListRepository providesPhotoListRepository(EventBus eventBus, FirebaseAPI firebaseAPI) {
        return new PhotoListRepositoryImpl(eventBus, firebaseAPI);
    }

    @Provides @Singleton
    PhotoListAdapter providesPhotoListAdapter(Util util, ImageLoader imageLoader, List<Photo> photos, OnItemClickListener clickListener) {
        return new PhotoListAdapter(util, imageLoader, photos, clickListener);
    }

    @Provides @Singleton
    OnItemClickListener providesOnItemClickListener() {
        return clickListener;
    }

    @Provides @Singleton
    List<Photo> providesPhotos() {
        return new ArrayList<Photo>();
    }
}
