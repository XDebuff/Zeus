package com.zeus.core.base;

import android.widget.Toast;

import com.zeus.core.ui.IHintView;

import androidx.fragment.app.Fragment;

/***************************************************
 * Author: Debuff 
 * Data: 2017/5/21
 * Description: 
 ***************************************************/
public class BaseFragment extends Fragment implements IHintView {
    protected final String TAG = this.getClass().getSimpleName();

    public void toast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hiddenLoadingView() {

    }

    @Override
    public void showEmptyView() {

    }

    @Override
    public void hiddenEmptyView() {

    }

}
