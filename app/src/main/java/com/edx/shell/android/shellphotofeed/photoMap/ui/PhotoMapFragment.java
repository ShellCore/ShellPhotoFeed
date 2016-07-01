package com.edx.shell.android.shellphotofeed.photoMap.ui;

import android.Manifest;
import android.app.FragmentManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.edx.shell.android.shellphotofeed.PhotoFeedApp;
import com.edx.shell.android.shellphotofeed.R;
import com.edx.shell.android.shellphotofeed.entities.Photo;
import com.edx.shell.android.shellphotofeed.photoMap.PhotoMapPresenter;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PhotoMapFragment extends Fragment implements PhotoMapView, OnMapReadyCallback {

    // Constantes
    private static final int PERMISSIONS_REQUEST_LOCATION = 1;

    // Variables
    private GoogleMap map;

    // Servicios
    @Inject
    PhotoMapPresenter presenter;

    // Componentes
    @Bind(R.id.map_container)
    FrameLayout mapContainer;

    public PhotoMapFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupInjection();
        presenter.onCreate();
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
        View v = inflater.inflate(R.layout.fragment_photo_map, container, false);
        ButterKnife.bind(this, v);
        presenter.subscribe();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        android.support.v4.app.FragmentManager fm = getChildFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void setupInjection() {
        PhotoFeedApp app = (PhotoFeedApp) getActivity().getApplication();
        app.getPhotoMapComponent(this, this).inject(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(
                        new String[]{
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                        }, PERMISSIONS_REQUEST_LOCATION);
            }
            return;
        }
        map.setMyLocationEnabled(true);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_LOCATION:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (map != null) {
                        map.setMyLocationEnabled(true);
                    }
                }
                return;
            default:
                break;
        }
    }

    @Override
    public void addPhoto(Photo photo) {

    }

    @Override
    public void removePhoto(Photo photo) {

    }

    @Override
    public void onPhotoError(String error) {

    }
}
