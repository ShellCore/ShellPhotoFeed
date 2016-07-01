package com.edx.shell.android.shellphotofeed.photoList;

import com.edx.shell.android.shellphotofeed.domain.FirebaseAPI;
import com.edx.shell.android.shellphotofeed.domain.FirebaseActionListenerCallback;
import com.edx.shell.android.shellphotofeed.domain.FirebaseEventListenerCallback;
import com.edx.shell.android.shellphotofeed.entities.Photo;
import com.edx.shell.android.shellphotofeed.libs.base.EventBus;
import com.edx.shell.android.shellphotofeed.photoList.events.PhotoListEvent;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

public class PhotoListRepositoryImpl implements PhotoListRepository {

    // Servicios
    private EventBus eventBus;
    private FirebaseAPI firebaseAPI;

    public PhotoListRepositoryImpl(EventBus eventBus, FirebaseAPI firebaseAPI) {
        this.eventBus = eventBus;
        this.firebaseAPI = firebaseAPI;
    }

    @Override
    public void subscribe() {
        firebaseAPI.checkForData(new FirebaseActionListenerCallback() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onError(FirebaseError error) {
                if (error != null) {
                    post(PhotoListEvent.DELETE_EVENT, error.getMessage());
                } else {
                    post(PhotoListEvent.DELETE_EVENT, "");
                }
            }
        });

        firebaseAPI.subscribe(new FirebaseEventListenerCallback() {
            @Override
            public void onChildAdded(DataSnapshot snapshot) {
                Photo photo = snapshot.getValue(Photo.class);
                photo.setId(snapshot.getKey());

                String email = firebaseAPI.getAuthEmail();
                boolean publishedByMe = photo.getEmail().equals(email);
                photo.setPublishedByMe(publishedByMe);
                post(PhotoListEvent.READ_EVENT, photo);
            }

            @Override
            public void onChildRemoved(DataSnapshot snapshot) {
                Photo photo = snapshot.getValue(Photo.class);
                photo.setId(snapshot.getKey());
                post(PhotoListEvent.DELETE_EVENT, photo);
            }

            @Override
            public void onCancelled(FirebaseError error) {
                post(PhotoListEvent.READ_EVENT, error.getMessage());
            }
        });
    }

    @Override
    public void unsubscribe() {
        firebaseAPI.unsubscribe();
    }

    @Override
    public void removePhoto(final Photo photo) {
        firebaseAPI.remove(photo, new FirebaseActionListenerCallback() {
            @Override
            public void onSuccess() {
                post(PhotoListEvent.DELETE_EVENT, photo);
            }

            @Override
            public void onError(FirebaseError error) {
                post(PhotoListEvent.DELETE_EVENT, error.getMessage());
            }
        });
    }

    private void post(int type, String error, Photo photo) {
        PhotoListEvent event = new PhotoListEvent();
        event.setType(type);
        event.setError(error);
        event.setPhoto(photo);
        eventBus.post(event);
    }

    private void post(int type, String error) {
        post(type, error, null);
    }

    private void post(int type, Photo photo) {
        post(type, null, photo);
    }
}
