package com.edx.shell.android.shellphotofeed.photoList.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edx.shell.android.shellphotofeed.R;
import com.edx.shell.android.shellphotofeed.domain.Util;
import com.edx.shell.android.shellphotofeed.entities.Photo;
import com.edx.shell.android.shellphotofeed.libs.base.ImageLoader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.ViewHolder> {

    // Servicios
    private Util util;
    private ImageLoader imageLoader;

    // Variables
    private List<Photo> photos;
    private OnItemClickListener clickListener;

    public PhotoListAdapter(Util util, ImageLoader imageLoader, List<Photo> photos, OnItemClickListener clickListener) {
        this.util = util;
        this.imageLoader = imageLoader;
        this.photos = photos;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_photos, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Photo currentPhoto = photos.get(position);
        holder.setOnItemClickLister(currentPhoto, clickListener);

        imageLoader.load(holder.imgPhoto, currentPhoto.getUrl());
        imageLoader.load(holder.imgAvatar, util.getAvatarUrl(currentPhoto.getEmail()));
        holder.txtUser.setText(currentPhoto.getEmail());

        double lat = currentPhoto.getLatitude();
        double lng = currentPhoto.getLongitude();

        if (lat != 0.0 && lng != 0.0) {
            holder.txtPlace.setText(util.getFromLocation(lat, lng));
            holder.txtPlace.setVisibility(View.VISIBLE);
        } else {
            holder.txtPlace.setVisibility(View.GONE);
        }

        if (currentPhoto.isPublishedByMe()) {
            holder.btnDelete.setVisibility(View.VISIBLE);
        } else {
            holder.btnDelete.setVisibility(View.GONE);
        }
    }

    public void addPhoto(Photo photo) {
        photos.add(0, photo);
        notifyDataSetChanged();
    }

    public void removePhoto(Photo photo) {
        photos.remove(photo);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // Componentes
        @Bind(R.id.img_avatar)
        CircleImageView imgAvatar;
        @Bind(R.id.txt_user)
        TextView txtUser;
        @Bind(R.id.lnr_header)
        LinearLayout lnrHeader;
        @Bind(R.id.img_photo)
        ImageView imgPhoto;
        @Bind(R.id.txt_place)
        TextView txtPlace;
        @Bind(R.id.btn_share)
        ImageButton btnShare;
        @Bind(R.id.btn_delete)
        ImageButton btnDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setOnItemClickLister(final Photo photo, final OnItemClickListener listener) {
            txtPlace.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onPlaceClick(photo);
                }
            });

            btnShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onShareClick(photo, imgPhoto);
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDeleteClick(photo);
                }
            });
        }
    }
}
