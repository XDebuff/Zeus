package com.zeus.core.cache;

import android.os.Environment;

import com.zeus.utils.java.FileUtils;

import java.io.File;
import java.util.List;

/***************************************************
 * Author: Debuff 
 * Data: 2017/5/21
 * Description: 本地文件缓存
 ***************************************************/
@Deprecated
public class FileCache implements ICache<String, File> {

    private static FileCache fileCache;
    private FileUtils mFileUtils;
    private final String mRootDir;

    private FileCache() {
        super();
        mRootDir = Environment.getExternalStorageDirectory().toString() + File.separator
                + "magicconch" + File.separator;
        mFileUtils = new FileUtils();
    }

    public static FileCache instance() {
        if (fileCache == null) {
            synchronized (FileCache.class) {
                if (fileCache == null) {
                    fileCache = new FileCache();
                }
            }
        }
        return fileCache;
    }

    @Override
    public boolean isExpire() {
        return false;
    }

    @Override
    public File loadCache(String key) {
        return new File(mRootDir + key);
    }

    @Override
    public List<File> loadCacheList(String key) {
        return null;
    }

    public boolean save(String key, String content) {
        save(key, content, false);
        return true;
    }

    @Override
    public boolean save(String key, String content, boolean append) {
        mFileUtils.writeToFile(mRootDir + key, content, append);
        return true;
    }

    @Override
    public boolean delete(String key) {
        mFileUtils.deleteFile(mRootDir + key);
        return false;
    }

    public String getStringContent(String key) {
        File file = loadCache(key);
        if (file.exists()) {
            return mFileUtils.readFile(file.getAbsolutePath());
        }
        return null;
    }
}
