package com.edx.shell.android.shellphotofeed.domain;

import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Util {

    // Ubicación para obtener el avatar del servicio gravatar
    public static final String GRAVATAR_URL = "http://www.gravatar.com/avatar/";
    // Variable para obtener la ubicación a partir de latitud y longitud
    private Geocoder geocoder;

    public Util(Geocoder geocoder) {
        this.geocoder = geocoder;
    }

    public String getAvatarUrl(String email) {
        return GRAVATAR_URL + md5(email) + "?s=64";
    }

    public String getFromLocation(double latitude, double longitude) {
        String result = "";
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            Address address = addresses.get(0);
            for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                result += address.getAddressLine(i) + ", ";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Función para obtener el avatar de md5
    private String md5(final String s) {
        final String MD5 = "MD5";
        try {
            MessageDigest digest = MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest :
                    messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() > 2) {
                    h = "0" + h;
                }
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
