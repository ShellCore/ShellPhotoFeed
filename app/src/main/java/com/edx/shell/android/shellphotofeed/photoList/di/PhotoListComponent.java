package com.edx.shell.android.shellphotofeed.photoList.di;

import com.edx.shell.android.shellphotofeed.PhotoFeedAppModule;
import com.edx.shell.android.shellphotofeed.domain.di.DomainModule;
import com.edx.shell.android.shellphotofeed.libs.di.LibsModule;
import com.edx.shell.android.shellphotofeed.photoList.ui.PhotoListFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {PhotoListModule.class, DomainModule.class, LibsModule.class, PhotoFeedAppModule.class})
public interface PhotoListComponent {
    void inject(PhotoListFragment fragment);
}
