package com.edx.shell.android.shellphotofeed.main.di;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.edx.shell.android.shellphotofeed.domain.FirebaseAPI;
import com.edx.shell.android.shellphotofeed.libs.base.EventBus;
import com.edx.shell.android.shellphotofeed.libs.base.ImageStorage;
import com.edx.shell.android.shellphotofeed.main.MainPresenter;
import com.edx.shell.android.shellphotofeed.main.MainPresenterImpl;
import com.edx.shell.android.shellphotofeed.main.MainRepository;
import com.edx.shell.android.shellphotofeed.main.MainRepositoryImpl;
import com.edx.shell.android.shellphotofeed.main.SessionInteractor;
import com.edx.shell.android.shellphotofeed.main.SessionInteractorImpl;
import com.edx.shell.android.shellphotofeed.main.UploadInteractor;
import com.edx.shell.android.shellphotofeed.main.UploadInteractorImpl;
import com.edx.shell.android.shellphotofeed.main.ui.MainView;
import com.edx.shell.android.shellphotofeed.main.ui.adapters.MainSectionsPagerAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    // Variables
    private MainView view;
    private String[] titles;
    private Fragment[] fragments;
    private FragmentManager fragmentManager;

    public MainModule(MainView view, String[] titles, Fragment[] fragments, FragmentManager fragmentManager) {
        this.view = view;
        this.titles = titles;
        this.fragments = fragments;
        this.fragmentManager = fragmentManager;
    }

    @Provides @Singleton
    MainView providesMainView() {
        return view;
    }

    @Provides @Singleton
    MainPresenter providesMainPresenter(MainView view, EventBus eventBus, SessionInteractor sessionInteractor, UploadInteractor uploadInteractor) {
        return new MainPresenterImpl(view, eventBus, sessionInteractor, uploadInteractor);
    }

    @Provides @Singleton
    UploadInteractor providesUploadInteractor(MainRepository repository) {
        return new UploadInteractorImpl(repository);
    }

    @Provides @Singleton
    SessionInteractor providesSessionInteractor(MainRepository repository) {
        return new SessionInteractorImpl(repository);
    }

    @Provides @Singleton
    MainRepository providesMainRepository(EventBus eventBus, FirebaseAPI firebaseAPI, ImageStorage imageStorage) {
        return new MainRepositoryImpl(eventBus, firebaseAPI, imageStorage);
    }

    @Provides @Singleton
    MainSectionsPagerAdapter providesMainSectionsPagerAdapter(FragmentManager fm, String[] titles, Fragment[] fragments) {
        return new MainSectionsPagerAdapter(fm, titles, fragments);
    }
    
    @Provides @Singleton
    FragmentManager providesFragmentManager() {
        return fragmentManager;
    }

    @Provides @Singleton
    Fragment[] providesFragmentArrayForAdapter() {
        return fragments;
    }

    @Provides @Singleton
    String[] providesStringArrayForAdapter() {
        return titles;
    }
}
