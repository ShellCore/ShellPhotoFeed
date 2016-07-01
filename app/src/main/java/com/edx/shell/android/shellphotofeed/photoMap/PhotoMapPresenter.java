package com.edx.shell.android.shellphotofeed.photoMap;

import com.edx.shell.android.shellphotofeed.photoMap.events.PhotoMapEvent;

public interface PhotoMapPresenter {
    void onCreate();
    void onDestroy();

    void subscribe();
    void unsubscribe();

    void onEventMainThread(PhotoMapEvent event);
}
