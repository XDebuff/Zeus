package com.zeus.ui;

/***************************************************
 * Author: Debuff 
 * Data: 2018/12/5
 * Description: 
 ***************************************************/
public interface IHintView extends IView {
    void toast(String msg);

    void showLoadingView();

    void hiddenLoadingView();

    void showEmptyView();

    void hiddenEmptyView();
}
