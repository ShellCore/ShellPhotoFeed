package com.edx.shell.android.shellphotofeed.login.events;

public class LoginEvent {
    public static final int ON_SIGNIN_ERROR = 0;
    public static final int ON_SIGNUP_ERROR = 1;
    public static final int ON_SIGNIN_SUCCESS = 2;
    public static final int ON_SIGNUP_SUCCESS = 3;
    public static final int ON_FAILED_TO_RECOVER_SESSION = 4;

    private int eventType;
    private String errorMessage;
    private String currentUserEmail;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getCurrentUserEmail() {
        return currentUserEmail;
    }

    public void setCurrentUserEmail(String currentUserEmail) {
        this.currentUserEmail = currentUserEmail;
    }
}
