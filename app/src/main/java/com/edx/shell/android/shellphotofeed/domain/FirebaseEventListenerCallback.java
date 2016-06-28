package com.edx.shell.android.shellphotofeed.domain;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

public interface FirebaseEventListenerCallback {
    void onChildAdded(DataSnapshot snapshot);
    void onChildRemoved(DataSnapshot snapshot);
    void onCancelled(FirebaseError error);
}
