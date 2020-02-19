package com.zeus.ui.recyclerview;


public interface ItemTouchListener {
    void onItemDismiss(int position);

    void onItemMove(int fromPosition, int toPosition);
}