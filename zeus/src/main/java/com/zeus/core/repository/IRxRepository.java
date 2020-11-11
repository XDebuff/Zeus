package com.zeus.core.repository;

import java.util.List;

import io.reactivex.Observable;

/***************************************************
 * Author: Debuff
 * Data: 2017/5/21
 * Description:
 ***************************************************/

public interface IRxRepository<T, D> extends IRepository<T> {

    Observable<Boolean> create(T data);

    Observable<Boolean> create(List<T> data);

    Observable<Boolean> update(T data);

    Observable<Boolean> update(List<T> data);

    Observable<Boolean> delete(T data);

    Observable<Boolean> deleteById(D data);

    Observable<Boolean> delete(List<T> data);

    Observable<Boolean> deleteByIds(List<D> data);

    Observable<Boolean> deleteAll();

    Observable<T> query(D id);

    Observable<List<T>> queryAll();
}
