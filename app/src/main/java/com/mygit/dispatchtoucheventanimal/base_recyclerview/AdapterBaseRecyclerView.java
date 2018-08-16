package com.mygit.dispatchtoucheventanimal.base_recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mygit.dispatchtoucheventanimal.R;

import java.util.List;

/**
 * Created by admin on 2018/7/25.
 */

public class AdapterBaseRecyclerView extends RecyclerView.Adapter {

    private List<String> mStrList;
    private OnItemClickListener onItemClickListener;

    /**
     * 设置点击事件
     * RecyclerView并没有像ListView的那样可以设置点击事件以及长按点击事件，
     * 这个需要我们可以在adapter中去设置回调的方式实现
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, String data);
    }

    private class MyOnClickListener implements View.OnClickListener {
        private int position;
        private String data;

        public MyOnClickListener(int position, String data) {
            this.position = position;
            this.data = data;
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v, position, data);
        }
    }

    /**
     * 插入一条数据
     *
     * @param index 下标
     * @param s     数据
     */
    public void addItem(int index, String s) {
        mStrList.add(index, s);
        notifyItemInserted(index);
    }

    /**
     * 删除一条数据
     *
     * @param index 下标
     */
    public void deleteItem(int index) {
        mStrList.remove(index);
        notifyItemRemoved(index);
    }

    public AdapterBaseRecyclerView(List<String> strList) {
        this.mStrList = strList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_str, parent, false);
        AdapterBaseRecyclerView.VHodler viewHolder = new AdapterBaseRecyclerView.VHodler(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String s = mStrList.get(position);
        VHodler mViewHolder = (VHodler) holder;
        int adapterPosition = holder.getAdapterPosition();
        if (onItemClickListener != null) {
            mViewHolder.itemView.setOnClickListener(new MyOnClickListener(position, mStrList.get(adapterPosition)));
        } else {

        }
        if (s != null) {
            mViewHolder.mText.setText(s);
        } else {
            mViewHolder.mText.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return mStrList.size();
    }

    class VHodler extends RecyclerView.ViewHolder {
        TextView mText;

        public VHodler(View itemView) {
            super(itemView);
            mText = itemView.findViewById(R.id.tv_str);
        }
    }
}
