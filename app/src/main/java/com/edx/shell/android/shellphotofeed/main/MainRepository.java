package com.edx.shell.android.shellphotofeed.main;

import android.location.Location;

public interface MainRepository {
    void logout();
    void uploadPhoto(Location location, String path);
}
