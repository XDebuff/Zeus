package com.zeus.core.cache;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zeus.utils.java.FileUtils;

import java.io.File;
import java.util.List;

/***************************************************
 * Author: Debuff 
 * Data: 2019/2/14
 * Description: Gson的泛型转换问题
 ***************************************************/
@Deprecated
public class DataCache<I, O> implements ICache<I, O> {

    private FileCache mFileCache;

    public DataCache() {
        super();
        mFileCache = FileCache.instance();
    }

    @Override
    public boolean isExpire() {
        return mFileCache.isExpire();
    }

    @Override
    public O loadCache(String key) {
        return null;
    }

    @Override
    public List<O> loadCacheList(String key) {
        File file = FileCache.instance().loadCache(key);
        if (!file.exists()) {
//            mView.fail("未找到缓存数据");
            return null;
        }
        String content = new FileUtils().readFile(file.getAbsolutePath());
        List<O> dataCache = new Gson().fromJson(content, new TypeToken<List<O>>() {
        }.getType());
        return dataCache;
    }

    @Override
    public boolean save(String key, I content, boolean cover) {
        return FileCache.instance()
                .save(key, new Gson().toJson(content));
    }

    @Override
    public boolean delete(String key) {
        return false;
    }
}
