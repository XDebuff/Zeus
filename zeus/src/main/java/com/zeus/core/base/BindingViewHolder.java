package com.zeus.core.base;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/***************************************************
 * Author: Debuff
 * Data: 2019/10/21
 * Description:
 ***************************************************/
public class BindingViewHolder extends RecyclerView.ViewHolder {

    public ViewDataBinding getViewDataBinding() {
        return mViewDataBinding;
    }

    public void setViewDataBinding(ViewDataBinding viewDataBinding) {
        mViewDataBinding = viewDataBinding;
    }

    ViewDataBinding mViewDataBinding;

    public BindingViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}
