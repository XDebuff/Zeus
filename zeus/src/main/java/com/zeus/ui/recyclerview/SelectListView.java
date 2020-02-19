package com.zeus.ui.recyclerview;

import java.util.List;

/***************************************************
 * Author: Debuff 
 * Data: 2018/12/9
 * Description: 
 ***************************************************/
public interface SelectListView<V, T> {

    List<T> getSelectedList();

    List<T> getUnSelectedList();

    void setItemSelect(int position);

    void removeSelected(int position);

    boolean isSelected(int position);

    void setSelectList(int[] position);

    void updateSelectedView(int position, V viewHolder);

    void updateUnSelectView(int position, V viewHolder);

}
