package com.edx.shell.android.shellphotofeed.login;

import com.edx.shell.android.shellphotofeed.domain.FirebaseAPI;
import com.edx.shell.android.shellphotofeed.domain.FirebaseActionListenerCallback;
import com.edx.shell.android.shellphotofeed.libs.base.EventBus;
import com.edx.shell.android.shellphotofeed.login.events.LoginEvent;
import com.firebase.client.FirebaseError;

public class LoginRepositoryImpl implements LoginRepository {

    private EventBus eventBus;
    private FirebaseAPI firebaseAPI;

    public LoginRepositoryImpl(EventBus eventBus, FirebaseAPI firebaseAPI) {
        this.eventBus = eventBus;
        this.firebaseAPI = firebaseAPI;
    }

    @Override
    public void signin(final String email, String pass) {
        if (email != null && pass != null) {
            firebaseAPI.login(email, pass, new FirebaseActionListenerCallback() {
                @Override
                public void onSuccess() {
                    String email = firebaseAPI.getAuthEmail();
                    postEvent(LoginEvent.ON_SIGNIN_SUCCESS, email);
                }

                @Override
                public void onError(FirebaseError error) {
                    postEvent(LoginEvent.ON_SIGNIN_ERROR, error.getMessage(), null);
                }
            });
        } else {
            firebaseAPI.checkForSession(new FirebaseActionListenerCallback() {
                @Override
                public void onSuccess() {
                    String email = firebaseAPI.getAuthEmail();
                    postEvent(LoginEvent.ON_SIGNIN_SUCCESS, email);
                }

                @Override
                public void onError(FirebaseError error) {
                    postEvent(LoginEvent.ON_FAILED_TO_RECOVER_SESSION);
                }
            });
        }
    }

    @Override
    public void signup(final String email, final String pass) {
        firebaseAPI.signup(email, pass, new FirebaseActionListenerCallback() {
            @Override
            public void onSuccess() {
                postEvent(LoginEvent.ON_SIGNUP_SUCCESS);
                signin(email, pass);
            }

            @Override
            public void onError(FirebaseError error) {
                postEvent(LoginEvent.ON_SIGNUP_ERROR, error.getMessage(), null);
            }
        });
    }

    private void postEvent(int type, String errorMessage, String currentUserEmail) {
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        loginEvent.setErrorMessage(errorMessage);
        loginEvent.setCurrentUserEmail(currentUserEmail);
        eventBus.post(loginEvent);
    }

    private void postEvent(int type) {
        postEvent(type, null, null);
    }

    private void postEvent(int type, String currentUserEmail) {
        postEvent(type, null, currentUserEmail);
    }
}
