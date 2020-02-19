package com.zeus.ui.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zeus.R;

import androidx.recyclerview.widget.RecyclerView;


/*******************************************************************
 * author：zhangxl
 * createTime：2017/6/2
 * description :
 *******************************************************************/
public class SampleViewHolder extends RecyclerView.ViewHolder {

    public TextView titleView;
    public ImageView iconView;
    public TextView descView;
    public TextView titleExtView;

    public SampleViewHolder(View itemView) {
        super(itemView);
        titleView = (TextView) itemView.findViewById(R.id.title_view);
        descView = (TextView) itemView.findViewById(R.id.desc_view);
        iconView = (ImageView) itemView.findViewById(R.id.icon_view);
        titleExtView = (TextView) itemView.findViewById(R.id.title_ext_view);
    }
}
