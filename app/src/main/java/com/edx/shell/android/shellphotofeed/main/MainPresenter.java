package com.edx.shell.android.shellphotofeed.main;

import android.location.Location;

import com.edx.shell.android.shellphotofeed.main.events.MainEvent;

public interface MainPresenter {
    void onCreate();
    void onDestroy();

    void logout();
    void uploadPhoto(Location location, String path);
    void onEventMainThread(MainEvent event);
}
