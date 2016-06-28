package com.edx.shell.android.shellphotofeed.login.di;

import com.edx.shell.android.shellphotofeed.PhotoFeedAppModule;
import com.edx.shell.android.shellphotofeed.domain.di.DomainModule;
import com.edx.shell.android.shellphotofeed.libs.di.LibsModule;
import com.edx.shell.android.shellphotofeed.login.ui.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {LoginModule.class, DomainModule.class, LibsModule.class, PhotoFeedAppModule.class})
public interface LoginComponent {
    void inject(LoginActivity activity);
}
