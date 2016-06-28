package com.edx.shell.android.shellphotofeed.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Photo {

    private String url; // Obtenido de cloudinary con firebase
    private String email; // Usuario que publicó la fotografía
    private double latitude; // Ubicación geográfica donde se capturó la imagen
    private double longitude;

    @JsonIgnore
    private boolean publishedByMe; // Si esta imagen fue publicada por la persona logueada. Ignorado en firebase
    @JsonIgnore
    private String id; // Identificador en firebase

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean isPublishedByMe() {
        return publishedByMe;
    }

    public void setPublishedByMe(boolean publishedByMe) {
        this.publishedByMe = publishedByMe;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
