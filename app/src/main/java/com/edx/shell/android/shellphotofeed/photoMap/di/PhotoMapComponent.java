package com.edx.shell.android.shellphotofeed.photoMap.di;

import com.edx.shell.android.shellphotofeed.PhotoFeedAppModule;
import com.edx.shell.android.shellphotofeed.domain.di.DomainModule;
import com.edx.shell.android.shellphotofeed.libs.di.LibsModule;
import com.edx.shell.android.shellphotofeed.photoMap.ui.PhotoMapFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {PhotoMapModule.class, DomainModule.class, LibsModule.class, PhotoFeedAppModule.class})
public interface PhotoMapComponent {
    void inject(PhotoMapFragment target);
}
