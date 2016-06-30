package com.edx.shell.android.shellphotofeed.main.events;

public class MainEvent {

    // Constantes
    public static final int UPLOAD_INIT = 0;
    public static final int UPLOAD_COMPLETE = 1;
    public static final int UPLOAD_ERROR = 2;

    // Variables
    private int type;
    private String error;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
