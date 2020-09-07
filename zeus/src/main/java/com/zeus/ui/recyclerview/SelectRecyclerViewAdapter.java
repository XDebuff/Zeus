package com.zeus.ui.recyclerview;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/***************************************************
 * Author: Debuff 
 * Data: 2018/12/9
 * Description: 
 ***************************************************/
public abstract class SelectRecyclerViewAdapter<V extends RecyclerView.ViewHolder, T>
        extends SimpleRecyclerViewAdapter<V, T>
        implements SelectListView<V, T> {

    protected List<T> mSelectedList = new ArrayList<>();

    private List<T> mUnSelectList = new ArrayList<>();

    private RecyclerView mRecyclerView;

    public void openSelectMode() {
        this.openSelectMode = true;
    }

    public void closeSelectMode() {
        this.openSelectMode = false;
        mSelectedList.clear();
        mUnSelectList.clear();
    }

    private boolean openSelectMode = true;

    public SelectRecyclerViewAdapter(Context context, RecyclerView recyclerView) {
        super(context);
        mRecyclerView = recyclerView;
    }

    public SelectRecyclerViewAdapter(Context context, RecyclerView recyclerView, List<T> datum) {
        super(context, datum);
        mRecyclerView = recyclerView;
    }

    @Override
    public void clear() {
        super.clear();
        clearSelectState();
    }

    public void clearSelectState() {
        mSelectedList.clear();
        mUnSelectList.clear();
    }

    @Override
    public List<T> getSelectedList() {
        return mSelectedList;
    }

    @Override
    public List<T> getUnSelectedList() {
        return mUnSelectList;
    }

    @Override
    public boolean isSelected(int position) {
        return mSelectedList.contains(getItem(position));
    }

    @Override
    public void setItemSelect(int position) {
        if (!openSelectMode) {
            return;
        }
        mSelectedList.add(getItem(position));
//        mSelectPosition.add(position);
        mUnSelectList.remove(getItem(position));
        updateSelectedView(position, (V) mRecyclerView.findViewHolderForAdapterPosition(position));
    }

    @Override
    public void removeSelected(int position) {
        if (!openSelectMode) {
            return;
        }
        mSelectedList.remove(getItem(position));
//        mSelectPosition.remove(position);
        mUnSelectList.add(getItem(position));
        updateUnSelectView(position, (V) mRecyclerView.findViewHolderForAdapterPosition(position));
    }


    @Override
    public void setSelectList(int[] position) {
        for (int i : position) {
            mSelectedList.add(getItem(i));
        }
        notifyDataSetChanged();
    }
}
