package com.edx.shell.android.shellphotofeed.domain.di;

import com.edx.shell.android.shellphotofeed.PhotoFeedAppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {DomainModule.class, PhotoFeedAppModule.class})
public interface DomainComponent {
}
