package com.example.androidmvp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<String> mData;
    private OnItemClickListener mListener;

    public RecycleViewAdapter(Context context, List<String> data, OnItemClickListener listener) {
        this.mContext = context;
        this.mData = data;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.my_item, viewGroup, false)); // 传入item布局
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, final int i) {
        viewHolder.textView.setText(mData.get(i));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext, "内部点击", Toast.LENGTH_SHORT).show();
                mListener.onClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_item); // 通过布局找子控件,itemView是item布局，即my_item.xml的inflate
        }
    }

    // 外部接口回调监听
    public interface OnItemClickListener {
        void onClick(int pos);
    }
}
