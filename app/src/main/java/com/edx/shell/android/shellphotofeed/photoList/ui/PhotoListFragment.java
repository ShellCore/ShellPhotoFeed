package com.edx.shell.android.shellphotofeed.photoList.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.edx.shell.android.shellphotofeed.PhotoFeedApp;
import com.edx.shell.android.shellphotofeed.R;
import com.edx.shell.android.shellphotofeed.entities.Photo;
import com.edx.shell.android.shellphotofeed.photoList.PhotoListPresenter;
import com.edx.shell.android.shellphotofeed.photoList.adapters.OnItemClickListener;
import com.edx.shell.android.shellphotofeed.photoList.adapters.PhotoListAdapter;

import java.io.ByteArrayOutputStream;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PhotoListFragment extends Fragment implements PhotoListView, OnItemClickListener {

    // Servicios
    @Inject
    PhotoListPresenter presenter;
    @Inject
    PhotoListAdapter adapter;

    // Componentes
    @Bind(R.id.recyclerList)
    RecyclerView recyclerList;
    @Bind(R.id.progressList)
    ProgressBar progressList;
    @Bind(R.id.frame_list_container)
    FrameLayout frameListContainer;

    public PhotoListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupInjection();
        presenter.onCreate();
    }

    private void setupInjection() {
        PhotoFeedApp app = (PhotoFeedApp) getActivity().getApplication();
        app.getPhotoListComponent(this, this, this)
                .inject(this);
    }

    @Override
    public void onDestroy() {
        presenter.unsubscribe();
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_list, container, false);
        ButterKnife.bind(this, view);
        setupRecyclerView();
        presenter.subscribe();
        return view;
    }

    private void setupRecyclerView() {
        recyclerList.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerList.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void showList() {
        recyclerList.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideList() {
        recyclerList.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressList.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressList.setVisibility(View.GONE);
    }

    @Override
    public void addPhoto(Photo photo) {
        adapter.addPhoto(photo);
    }

    @Override
    public void removePhoto(Photo photo) {
        adapter.removePhoto(photo);
    }

    @Override
    public void onPhotoError(String error) {
        Snackbar.make(frameListContainer, error, Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onPlaceClick(Photo photo) {
        Intent view = new Intent();
        view.setAction(Intent.ACTION_VIEW);
        view.setData(Uri.parse("geo:" + photo.getLatitude() + ", " + photo.getLongitude()));
        if (view.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(view);
        }
    }

    @Override
    public void onShareClick(Photo photo, ImageView view) {
        Bitmap bitmap = ((GlideBitmapDrawable) view.getDrawable()).getBitmap();
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpeg");

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images
                                .Media
                                .insertImage(getActivity().getContentResolver(), bitmap, null, null);
        Uri imageUri = Uri.parse(path);

        share.putExtra(Intent.EXTRA_STREAM, imageUri);
        startActivity(Intent.createChooser(share, getString(R.string.photolist_message_share)));
    }

    @Override
    public void onDeleteClick(Photo photo) {
        presenter.removePhoto(photo);
    }
}
