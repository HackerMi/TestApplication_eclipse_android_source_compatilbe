package com.android.mytest;

public class FileUtils {

    static {
        System.loadLibrary("ctsverifier_jni");
    }

    public native static boolean checkFileExec(String path);
}
