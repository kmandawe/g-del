package com.kensbunker.gdel.utils;

import com.kensbunker.gdel.request.GParcel;

public class DimensionsUtils {

    public static int getVolume(GParcel parcel) {
        return getVolume(parcel.getHeight(), parcel.getWidth(), parcel.getLength());
    }
    public static int getVolume(Integer height, Integer width, Integer length) {
        return height * width * length;
    }
}
