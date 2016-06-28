package com.edx.shell.android.shellphotofeed.login.di;

import com.edx.shell.android.shellphotofeed.domain.FirebaseAPI;
import com.edx.shell.android.shellphotofeed.libs.base.EventBus;
import com.edx.shell.android.shellphotofeed.login.LoginInteractor;
import com.edx.shell.android.shellphotofeed.login.LoginInteractorImpl;
import com.edx.shell.android.shellphotofeed.login.LoginPresenter;
import com.edx.shell.android.shellphotofeed.login.LoginPresenterImpl;
import com.edx.shell.android.shellphotofeed.login.LoginRepository;
import com.edx.shell.android.shellphotofeed.login.LoginRepositoryImpl;
import com.edx.shell.android.shellphotofeed.login.ui.LoginView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {
    private LoginView loginView;

    public LoginModule(LoginView loginView) {
        this.loginView = loginView;
    }

    @Provides @Singleton
    LoginView providesLoginView() {
        return loginView;
    }

    @Provides @Singleton
    LoginPresenter providesLoginPresenter(EventBus eventBus, LoginView view, LoginInteractor interactor) {
        return new LoginPresenterImpl(eventBus, view, interactor);
    }

    @Provides @Singleton
    LoginInteractor providesLoginInteractor(LoginRepository repository) {
        return new LoginInteractorImpl(repository);
    }

    @Provides @Singleton
    LoginRepository providesLoginRepository(EventBus eventBus, FirebaseAPI firebaseAPI) {
        return new LoginRepositoryImpl(eventBus, firebaseAPI);
    }
}
