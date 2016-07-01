package com.edx.shell.android.shellphotofeed;

import android.app.Application;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.edx.shell.android.shellphotofeed.domain.di.DomainModule;
import com.edx.shell.android.shellphotofeed.libs.di.LibsModule;
import com.edx.shell.android.shellphotofeed.login.di.DaggerLoginComponent;
import com.edx.shell.android.shellphotofeed.login.di.LoginComponent;
import com.edx.shell.android.shellphotofeed.login.di.LoginModule;
import com.edx.shell.android.shellphotofeed.login.ui.LoginView;
import com.edx.shell.android.shellphotofeed.main.di.DaggerMainComponent;
import com.edx.shell.android.shellphotofeed.main.di.MainComponent;
import com.edx.shell.android.shellphotofeed.main.di.MainModule;
import com.edx.shell.android.shellphotofeed.main.ui.MainView;
import com.edx.shell.android.shellphotofeed.photoList.adapters.OnItemClickListener;
import com.edx.shell.android.shellphotofeed.photoList.di.DaggerPhotoListComponent;
import com.edx.shell.android.shellphotofeed.photoList.di.PhotoListComponent;
import com.edx.shell.android.shellphotofeed.photoList.di.PhotoListModule;
import com.edx.shell.android.shellphotofeed.photoList.ui.PhotoListFragment;
import com.edx.shell.android.shellphotofeed.photoList.ui.PhotoListView;
import com.firebase.client.Firebase;

public class PhotoFeedApp extends Application {

    public static final String EMAIL_KEY = "email";
    public static final String SHARED_PREFERENCES_NAME = "UserPrefs";
    public static final String FIREBASE_URL = "https://shell-photo-feed.firebaseio.com/";

    private PhotoFeedAppModule photoFeedAppModule;
    private DomainModule domainModule;

    @Override
    public void onCreate() {
        super.onCreate();
        initFirebase();
        initModules();

    }

    private void initFirebase() {
        Firebase.setAndroidContext(this);
    }

    private void initModules() {
        photoFeedAppModule = new PhotoFeedAppModule(this);
        domainModule = new DomainModule(FIREBASE_URL);
    }

    public String getEmailKey() {
        return EMAIL_KEY;
    }

    public String getSharedPreferencesName() {
        return SHARED_PREFERENCES_NAME;
    }

    public LoginComponent getLoginComponent(LoginView view) {
        return DaggerLoginComponent.builder()
                .photoFeedAppModule(photoFeedAppModule)
                .domainModule(domainModule)
                .libsModule(new LibsModule(null))
                .loginModule(new LoginModule(view))
                .build();

    }

    public MainComponent getMainComponent(MainView view, FragmentManager fm, Fragment[] fragments, String[] titles) {
        return DaggerMainComponent.builder()
                .photoFeedAppModule(photoFeedAppModule)
                .domainModule(domainModule)
                .libsModule(new LibsModule(null))
                .mainModule(new MainModule(view, titles, fragments, fm))
                .build();
    }

    public PhotoListComponent getPhotoListComponent(PhotoListFragment fragment, PhotoListView view, OnItemClickListener clickListener) {
        return DaggerPhotoListComponent.builder()
                .photoFeedAppModule(photoFeedAppModule)
                .domainModule(domainModule)
                .libsModule(new LibsModule(fragment))
                .photoListModule(new PhotoListModule(view, clickListener))
                .build();

    }
}
