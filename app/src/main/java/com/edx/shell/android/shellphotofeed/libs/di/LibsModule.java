package com.edx.shell.android.shellphotofeed.libs.di;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.cloudinary.Cloudinary;
import com.cloudinary.android.Utils;
import com.edx.shell.android.shellphotofeed.libs.CloudinaryImageStorage;
import com.edx.shell.android.shellphotofeed.libs.GlideImageLoader;
import com.edx.shell.android.shellphotofeed.libs.GreenRobotEventBus;
import com.edx.shell.android.shellphotofeed.libs.base.EventBus;
import com.edx.shell.android.shellphotofeed.libs.base.ImageLoader;
import com.edx.shell.android.shellphotofeed.libs.base.ImageStorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LibsModule {
    private Fragment fragment;

    public LibsModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader(RequestManager manager) {
        return new GlideImageLoader(manager);
    }

    @Provides
    @Singleton
    RequestManager providesRequestManager(Fragment fragment) {
        return Glide.with(fragment);
    }

    @Provides
    @Singleton
    Fragment providesFragment() {
        return fragment;
    }

    @Provides
    @Singleton
    EventBus providesEventBus(org.greenrobot.eventbus.EventBus eventBus) {
        return new GreenRobotEventBus(eventBus);
    }
    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesLibraryEventBus() {
        return org.greenrobot.eventbus.EventBus.getDefault();
    }

    @Provides
    @Singleton
    ImageStorage providesImageStorage(Cloudinary cloudinary) {
        return new CloudinaryImageStorage(cloudinary);
    }
    @Provides
    @Singleton
    Cloudinary providesCloudinary(Context context) {
        return new Cloudinary(Utils.cloudinaryUrlFromContext(context));
    }
}
