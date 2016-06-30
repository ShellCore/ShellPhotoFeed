package com.edx.shell.android.shellphotofeed.main.di;

import com.edx.shell.android.shellphotofeed.PhotoFeedAppModule;
import com.edx.shell.android.shellphotofeed.domain.di.DomainModule;
import com.edx.shell.android.shellphotofeed.libs.di.LibsModule;
import com.edx.shell.android.shellphotofeed.main.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MainModule.class, DomainModule.class, LibsModule.class, PhotoFeedAppModule.class})
public interface MainComponent {
    void inject(MainActivity activity);
}
