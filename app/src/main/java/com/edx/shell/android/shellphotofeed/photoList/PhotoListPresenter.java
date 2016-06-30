package com.edx.shell.android.shellphotofeed.photoList;

import com.edx.shell.android.shellphotofeed.entities.Photo;
import com.edx.shell.android.shellphotofeed.photoList.events.PhotoListEvent;

public interface PhotoListPresenter {
    void onCreate();
    void onDestroy();

    void subscribe();
    void unsubscribe();
    void removePhoto(Photo photo);

    void onEventMainThread(PhotoListEvent event);
}
