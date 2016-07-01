package com.edx.shell.android.shellphotofeed.photoMap;

import com.edx.shell.android.shellphotofeed.domain.FirebaseAPI;
import com.edx.shell.android.shellphotofeed.domain.FirebaseEventListenerCallback;
import com.edx.shell.android.shellphotofeed.entities.Photo;
import com.edx.shell.android.shellphotofeed.libs.base.EventBus;
import com.edx.shell.android.shellphotofeed.photoMap.events.PhotoMapEvent;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

public class PhotoMapRepositoryImpl implements PhotoMapRepository {

    // Servicios
    private EventBus eventBus;
    private FirebaseAPI api;

    public PhotoMapRepositoryImpl(EventBus eventBus, FirebaseAPI api) {
        this.eventBus = eventBus;
        this.api = api;
    }

    @Override
    public void subscribe() {
        api.subscribe(new FirebaseEventListenerCallback() {
            @Override
            public void onChildAdded(DataSnapshot snapshot) {
                Photo photo = snapshot.getValue(Photo.class);
                photo.setId(snapshot.getKey());

                String email = api.getAuthEmail();
                boolean publishedByMe = photo.getEmail().equals(email);
                photo.setPublishedByMe(publishedByMe);
                post(PhotoMapEvent.READ_EVENT, photo);
            }

            @Override
            public void onChildRemoved(DataSnapshot snapshot) {
                Photo photo = snapshot.getValue(Photo.class);
                photo.setId(snapshot.getKey());

                post(PhotoMapEvent.DELETE_EVENT, photo);
            }

            @Override
            public void onCancelled(FirebaseError error) {
                post(PhotoMapEvent.READ_EVENT, error.getMessage());
            }
        });
    }

    @Override
    public void unsubscribe() {
        api.unsubscribe();
    }

    private void post(int type, String error, Photo currentPhoto) {
        PhotoMapEvent event = new PhotoMapEvent();
        event.setType(type);
        event.setError(error);
        event.setPhoto(currentPhoto);
        eventBus.post(event);
    }

    private void post(int type, String error) {
        post(type, error, null);
    }

    private void post(int type, Photo currentPhoto) {
        post(type, null, currentPhoto);
    }
}
