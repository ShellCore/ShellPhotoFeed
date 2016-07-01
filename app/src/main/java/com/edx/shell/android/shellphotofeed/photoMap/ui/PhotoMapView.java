package com.edx.shell.android.shellphotofeed.photoMap.ui;

import com.edx.shell.android.shellphotofeed.entities.Photo;

public interface PhotoMapView {
    void addPhoto(Photo photo);
    void removePhoto(Photo photo);
    void onPhotoError(String error);
}
