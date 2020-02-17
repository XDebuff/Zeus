package com.zeus.core.cache;

import java.util.List;

/***************************************************
 * Author: Debuff 
 * Data: 2017/5/21
 * Description: 
 ***************************************************/
public interface ICache<I, O> {
    /**
     * @return
     */
    boolean isExpire();

    /**
     * @param key
     * @return
     */
    O loadCache(String key);

    /**
     * @param key
     * @return
     */
    List<O> loadCacheList(String key);

    boolean save(String key, I content, boolean cover);

    boolean delete(String key);

}
