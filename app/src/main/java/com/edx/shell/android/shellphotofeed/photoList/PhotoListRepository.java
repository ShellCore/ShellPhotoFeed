package com.edx.shell.android.shellphotofeed.photoList;

import com.edx.shell.android.shellphotofeed.entities.Photo;

public interface PhotoListRepository {
    void subscribe();
    void unsubscribe();
    void removePhoto(Photo photo);
}
