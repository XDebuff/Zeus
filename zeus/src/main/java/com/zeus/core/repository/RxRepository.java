package com.zeus.core.repository;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/***************************************************
 * Author: Debuff
 * Data: 2017/5/21
 * Description:
 ***************************************************/
public abstract class RxRepository<T, D> implements IRxRepository<T, D> {

    private static final Map<Class, Class> cacheModelClass = new HashMap<>();

    protected Context context;

    protected Dao<T, D> mDao;

    public RxRepository() {
    }

    public void init(Context context) {
        this.context = context;
    }

    @Override
    public Observable<Boolean> create(final T data) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) {
                int count = 0;
                try {
                    count = mDao.create(data);
                } catch (SQLException e1) {
                    e.onError(e1);
                    e1.printStackTrace();
                }
                e.onNext(count > 0);
                e.onComplete();
            }
        });
    }

    @Override
    public Observable<Boolean> create(final List<T> data) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) {
                int count = 0;
                try {
                    count = mDao.create(data);
                } catch (SQLException e1) {
                    e.onError(e1);
                    e1.printStackTrace();
                }
                e.onNext(count > 0);
                e.onComplete();
            }
        });
    }

    @Override
    public Observable<Boolean> update(final T data) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) {
                int count = 0;
                try {
                    count = mDao.update(data);
                } catch (SQLException e1) {
                    e.onError(e1);
                    e1.printStackTrace();
                }
                e.onNext(count > 0);
                e.onComplete();
            }
        });
    }

    @Override
    public Observable<Boolean> update(final List<T> datum) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) {
                int count = 0;
                try {
                    count = mDao.callBatchTasks(new Callable<Integer>() {
                        @Override
                        public Integer call() throws Exception {
                            int count = 0;
                            for (T data : datum) {
                                count += mDao.update(data);
                            }
                            return count;
                        }
                    });
                } catch (Exception e1) {
                    e.onError(e1);
                    e1.printStackTrace();
                }
                e.onNext(count >= datum.size());
                e.onComplete();
            }
        });
    }

    @Override
    public Observable<Boolean> delete(final T data) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) {
                int count = 0;
                try {
                    count = mDao.delete(data);
                } catch (SQLException e1) {
                    e.onError(e1);
                    e1.printStackTrace();
                }
                e.onNext(count > 0);
                e.onComplete();
            }
        });
    }

    @Override
    public Observable<Boolean> deleteById(final D id) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) {
                int count = 0;
                try {
                    count = mDao.deleteById(id);
                } catch (SQLException e1) {
                    e.onError(e1);
                    e1.printStackTrace();
                }
                e.onNext(count > 0);
                e.onComplete();
            }
        });
    }

    @Override
    public Observable<Boolean> deleteByIds(final List<D> ids) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) {
                int count = 0;
                try {
                    count = mDao.deleteIds(ids);
                } catch (SQLException e1) {
                    e.onError(e1);
                    e1.printStackTrace();
                }
                e.onNext(count > 0);
                e.onComplete();
            }
        });
    }

    @Override
    public Observable<Boolean> delete(final List<T> data) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) {
                int count = 0;
                try {
                    count = mDao.delete(data);
                } catch (SQLException e1) {
                    e.onError(e1);
                    e1.printStackTrace();
                }
                e.onNext(count > 0);
                e.onComplete();
            }
        });
    }

    @Override
    public Observable<Boolean> deleteAll() {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) {
                int count = 0;
                try {
                    count = mDao.deleteBuilder().delete();
                } catch (SQLException e1) {
                    e.onError(e1);
                    e1.printStackTrace();
                }
                e.onNext(count > 0);
                e.onComplete();
            }
        });
    }

    @Override
    public Observable<T> query(final D id) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> e) {
                T data = null;
                try {
                    data = mDao.queryForId(id);
                } catch (SQLException e1) {
                    e.onError(e1);
                    e1.printStackTrace();
                }
                e.onNext(data);
                e.onComplete();
            }
        });
    }

    @Override
    public Observable<List<T>> queryAll() {
        return Observable.create(new ObservableOnSubscribe<List<T>>() {
            @Override
            public void subscribe(ObservableEmitter<List<T>> e) {
                List<T> datum = null;
                try {
                    datum = mDao.queryForAll();
                } catch (SQLException e1) {
                    e.onError(e1);
                    e1.printStackTrace();
                }
                e.onNext(datum);
                e.onComplete();
            }
        });
    }

    public Class getModelClass() {
        Class clazz = cacheModelClass.get(getClass());
        if (cacheModelClass.get(getClass()) == null) {
            Log.d("RxRepository", "没有仓库类泛型缓存");
            clazz = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            cacheModelClass.put(getClass(), clazz);
        } else {
            Log.d("RxRepository", "获取仓库类泛型缓存");
        }
        return clazz;
    }
}

