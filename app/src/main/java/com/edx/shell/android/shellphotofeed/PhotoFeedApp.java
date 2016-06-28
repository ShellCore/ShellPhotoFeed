package com.edx.shell.android.shellphotofeed;

import android.app.Application;

import com.edx.shell.android.shellphotofeed.domain.di.DomainModule;
import com.firebase.client.Firebase;

public class PhotoFeedApp extends Application {

    public static final String EMAIL_KEY = "email";
    public static final String SHARED_PREFERENCES_NAME = "UserPrefs";
    public static final String FIREBASE_URL = "https://android-photo-share.firebaseio.com/";

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

    public String getFirebaseUrl() {
        return FIREBASE_URL;
    }
}
