package com.zeus.core.base;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/***************************************************
 * Author: Debuff 
 * Data: 2017/5/21
 * Description: 
 ***************************************************/
public class DataBingdingViewHolder extends RecyclerView.ViewHolder {

    public DataBingdingViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public ViewDataBinding getViewDataBinding() {
        return mViewDataBinding;
    }

    public void setViewDataBinding(ViewDataBinding viewDataBinding) {
        mViewDataBinding = viewDataBinding;
    }

    public ViewDataBinding mViewDataBinding;

}
