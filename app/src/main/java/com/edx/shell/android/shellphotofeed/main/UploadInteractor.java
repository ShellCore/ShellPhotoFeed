package com.edx.shell.android.shellphotofeed.main;

import android.location.Location;

public interface UploadInteractor {
    void execute(Location location, String path);
}
