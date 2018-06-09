package com.example.poo.progra2.xml;

import android.os.Environment;

import java.io.File;

abstract class DAO {

    protected static File file;
    protected static String fileName;

    public abstract void parseXml();

    public abstract void writeXml();

    public static boolean isExternalStorageReadOnly() {

        String extStorageState = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {

            return true;

        }

        return false;

    }

    public static boolean isExternalStorageAvailable() {

        String extStorageState = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {

            return true;

        }

        return false;

    }
}
