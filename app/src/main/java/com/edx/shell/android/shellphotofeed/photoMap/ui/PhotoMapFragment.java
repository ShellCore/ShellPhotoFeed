package com.edx.shell.android.shellphotofeed.photoMap.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.edx.shell.android.shellphotofeed.PhotoFeedApp;
import com.edx.shell.android.shellphotofeed.R;
import com.edx.shell.android.shellphotofeed.photoMap.PhotoMapPresenter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PhotoMapFragment extends Fragment {

    // Variables

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

    private void setupInjection() {
        PhotoFeedApp app = (PhotoFeedApp) getActivity().getApplication();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
