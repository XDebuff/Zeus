package com.zeus.core.repository;

import java.util.List;

import io.reactivex.Observable;

/***************************************************
 * Author: Debuff
 * Data: 2017/5/21
 * Description:
 ***************************************************/

public interface IRxRepository<T> extends IRepository<T> {

    Observable<Boolean> create(T data);

    Observable<Boolean> create(List<T> data);

    Observable<Boolean> update(T data);

    Observable<Boolean> update(List<T> data);

    Observable<Boolean> delete(T data);

    Observable<T> query(Integer id);

    Observable<List<T>> queryAll();
}