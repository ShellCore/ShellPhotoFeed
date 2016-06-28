package com.edx.shell.android.shellphotofeed.domain.di;

import android.content.Context;
import android.location.Geocoder;

import com.edx.shell.android.shellphotofeed.domain.FirebaseAPI;
import com.edx.shell.android.shellphotofeed.domain.Util;
import com.firebase.client.Firebase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DomainModule {
    String firebaseUrl;

    public DomainModule(String firebaseUrl) {
        this.firebaseUrl = firebaseUrl;
    }

    @Provides @Singleton
    FirebaseAPI providesFirebaseAPI(Firebase firebase) {
        return new FirebaseAPI(firebase);
    }

    @Provides @Singleton
    Firebase providesFirebase(String firebaseUrl) {
        return new Firebase(firebaseUrl);
    }

    @Provides @Singleton
    String providesFirebaseUrl() {
        return firebaseUrl;
    }

    @Provides @Singleton
    Util providesUtil(Geocoder geocoder) {
        return new Util(geocoder);
    }

    @Provides @Singleton
    Geocoder providesGeocoder(Context context) {
        return new Geocoder(context);
    }
}
