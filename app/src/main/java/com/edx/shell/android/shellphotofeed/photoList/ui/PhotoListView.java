package com.edx.shell.android.shellphotofeed.photoList.ui;

import com.edx.shell.android.shellphotofeed.entities.Photo;

public interface PhotoListView {
    void showList();
    void hideList();
    void showProgress();
    void hideProgress();

    void addPhoto(Photo photo);
    void removePhoto(Photo photo);
    void onPhotoError(String error);
}
