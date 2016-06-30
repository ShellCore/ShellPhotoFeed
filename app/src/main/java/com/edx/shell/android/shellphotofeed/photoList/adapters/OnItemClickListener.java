package com.edx.shell.android.shellphotofeed.photoList.adapters;

import android.widget.ImageView;

import com.edx.shell.android.shellphotofeed.entities.Photo;

public interface OnItemClickListener {
    void onPlaceClick(Photo photo);
    void onShareClick(Photo photo, ImageView view);
    void onDeleteClick(Photo photo);
}
