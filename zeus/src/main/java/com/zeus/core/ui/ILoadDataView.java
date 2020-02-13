package com.zeus.core.ui;

import java.util.List;

/***************************************************
 * Author: Debuff 
 * Data: 2018/12/5
 * Description: 
 ***************************************************/
public interface ILoadDataView<T> extends IHintView {

    void renderList(List<T> datum);

}
