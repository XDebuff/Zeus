package com.zeus.utils.app;

import android.os.Environment;

import com.zeus.core.cache.AppConfig;
import com.zeus.utils.java.FileUtils;

import java.io.File;

/***************************************************
 * Author: Debuff 
 * Data: 2017/5/21
 * Description: 
 ***************************************************/
public class SDCardUtils {

    private static final FileUtils mFileUtils = new FileUtils();
    private static final String mRootDir = Environment.getExternalStorageDirectory().toString() + File.separator
            + AppConfig.getInstance().getCacheDir() + File.separator;

    private SDCardUtils() {
        super();
    }

    public boolean isExists(String filePath) {
        return mFileUtils.isExists(filePath);
    }

    public static boolean save(String filePath, String content) {
        save(filePath, content, false);
        return true;
    }

    public static boolean save(String filePath, String content, boolean append) {
        mFileUtils.writeToFile(mRootDir + filePath, content, append);
        return true;
    }

    public static boolean delete(String filePath) {
        mFileUtils.deleteFile(mRootDir + filePath);
        return false;
    }

    public static String getStringContent(String filePath) {
        File file = new File(mRootDir + filePath);
        if (file.exists()) {
            return mFileUtils.readFile(file.getAbsolutePath());
        }
        return null;
    }
}
