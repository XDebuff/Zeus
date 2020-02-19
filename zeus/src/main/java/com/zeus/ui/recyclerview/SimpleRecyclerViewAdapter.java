package com.zeus.ui.recyclerview;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

/***************************************************
 * Author: Debuff 
 * Data: 2017/5/21
 * Description: 
 ***************************************************/
public abstract class SimpleRecyclerViewAdapter<V extends RecyclerView.ViewHolder, T>
        extends RecyclerView.Adapter<V>
        implements ItemTouchHelperAdapter {

    /**
     * 数据集
     */
    protected List<T> mDatum = new ArrayList<>();
    protected Context mContext;

    /**
     * item点击事件监听
     */
    private OnItemClickListener mIOnItemClickListener;
    private ItemTouchListener mListener;

    private ItemTouchHelper mItemTouchHelper;
    private SimpleItemTouchHelperCallback mSimpleItemTouchHelperCallback;

    public void setItemTouchListener(ItemTouchListener listener) {
        this.mListener = listener;
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (mListener != null) {
            mListener.onItemMove(fromPosition, toPosition);
        }
        Collections.swap(mDatum, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onMoveFinish(int fromPosition, int toPosition) {
//        Collections.swap(mDatum, fromPosition, toPosition);
//        notifyDataSetChanged();
    }

    @Override
    public void onItemDismiss(int position) {
        if (mListener != null) {
            mListener.onItemDismiss(position);
        }
        mDatum.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }

    public void deleteItem(int position) {
        onItemDismiss(position);
    }

    public void setDrag(boolean isAllowDrag) {
        if (mSimpleItemTouchHelperCallback != null) {
            mSimpleItemTouchHelperCallback.setCanDrag(isAllowDrag);
        }
    }

    public void setSwipe(boolean isAllowSwipe) {
        if (mSimpleItemTouchHelperCallback != null) {
            mSimpleItemTouchHelperCallback.setCanSwipe(isAllowSwipe);
        }
    }

    public interface OnItemClickListener {
        void OnItemClickListener(View view, int position);
    }

    /**
     * item长按事件监听
     */
    private OnItemLongClickListener mIOnItemLongClickListener;

    public interface OnItemLongClickListener {
        boolean OnItemLongClickListener(View view, int position);
    }

    public SimpleRecyclerViewAdapter(Context context) {
        super();
        mContext = context;
    }

    public SimpleRecyclerViewAdapter(Context context, List<T> datum) {
        this(context);
        initDatum(datum);
    }

    public void attachToRecyclerView(RecyclerView recyclerView) {
        mItemTouchHelper = new ItemTouchHelper(new SimpleItemTouchHelperCallback(this));
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }

    public List<T> getDatum() {
        return mDatum;
    }

    public void addData(T data) {
        mDatum.add(data);
    }

    public void addData(int position, T data) {
        mDatum.add(position, data);
    }

    /**
     * 初始化数据集
     *
     * @param datum
     */
    private void initDatum(List<T> datum) {
        //相同数据集不在初始化
        if (mDatum != null && mDatum.equals(datum)) {
            return;
        }
        //数据集合初始化
        if (datum == null) {
            datum = new ArrayList<>();
        }
        //更新数据
        if (mDatum != null) {
            mDatum.clear();
            mDatum.addAll(datum);
        }
    }

    /**
     * 设置数据
     *
     * @param datum
     */
    public void setDatum(List<T> datum) {
        initDatum(datum);
        this.notifyDataSetChanged();
    }

    public void addData(List<T> data) {
        //防止出现同一个数据集
        if (mDatum != null && mDatum.equals(data)) {
            return;
        }
        //数据集合初始化
        if (data == null) {
            return;
        }
        //更新数据
        if (mDatum == null) {
            mDatum = new ArrayList<>();
        }
        mDatum.addAll(data);
        this.notifyDataSetChanged();
    }

    /**
     * 清空数据
     */
    public void clear() {
        mDatum.clear();
        notifyDataSetChanged();
    }

    /**
     * 获取Item
     *
     * @param position
     * @return
     */
    public T getItem(int position) {
        if (mDatum == null || mDatum.size() == 0
                || position >= mDatum.size() || position < 0) {
            return null;
        }
        return mDatum.get(position);
    }

    /**
     * 绑定ViewHolder
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final V holder, int position) {
        //注册item点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIOnItemClickListener != null) {
                    mIOnItemClickListener.OnItemClickListener(holder.itemView, holder.getLayoutPosition());
                }
            }
        });
        //注册item长按事件
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mIOnItemLongClickListener != null) {
                    return mIOnItemLongClickListener.OnItemLongClickListener(holder.itemView, holder.getLayoutPosition());
                }
                return false;
            }
        });
        onBind(holder, position);
    }

    /**
     * 重设的bind方法
     *
     * @param holder
     * @param position
     */
    public abstract void onBind(V holder, int position);

    @Override
    public int getItemCount() {
        return mDatum.size();
    }

    /**
     * 设置item点击事件
     *
     * @param itemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        mIOnItemClickListener = itemClickListener;
    }

    /**
     * 设置item长按事件
     *
     * @param itemLongClickListener
     */
    public void setOnItemLongClickListener(OnItemLongClickListener itemLongClickListener) {
        mIOnItemLongClickListener = itemLongClickListener;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
