package com.emotionwave.ndklib;

import android.graphics.Bitmap;

public class NativeWrapper {
    static {
        System.loadLibrary("ndklib");
    }

    public native void stringFromJNI(Bitmap bmp);
}
