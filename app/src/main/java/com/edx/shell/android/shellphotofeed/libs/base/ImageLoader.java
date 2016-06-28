package com.edx.shell.android.shellphotofeed.libs.base;

import android.widget.ImageView;

public interface ImageLoader {
    void load(ImageView imageView, String Url);
    void setOnFinishedImageLoadingListener(Object listener);
}
