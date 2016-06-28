package com.edx.shell.android.shellphotofeed.libs.di;

import com.edx.shell.android.shellphotofeed.PhotoFeedAppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {LibsModule.class, PhotoFeedAppModule.class})
public class LibsComponent {
}
