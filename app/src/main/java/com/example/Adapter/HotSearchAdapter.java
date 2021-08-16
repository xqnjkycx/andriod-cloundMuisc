package com.example.Adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bean.HotSearchBean;
import com.example.mycloudmusic.R;

import java.util.List;

public class HotSearchAdapter extends RecyclerView.Adapter<HotSearchAdapter.ViewHolder> {
    private List<HotSearchBean> mDatas;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View itemView;
        TextView searchWord;
        View rootView;
        public ViewHolder(View v){
            super(v);
            itemView = v;
            searchWord = (TextView) v.findViewById(R.id.hot_search_textview);
        }
    }
    public HotSearchAdapter(List<HotSearchBean> list){
        mDatas = list;
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hot_search_item,parent,false);
        final ViewHolder holder = new ViewHolder(v);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                HotSearchBean item = mDatas.get(position);
                //在子布局中获取父布局就这样写
                EditText input = (EditText) ((View) parent.getParent()).findViewById(R.id.input);
                input.setText(item.getSearchWord());
                input.requestFocus();
            }
        });
        return holder;
    }
    public void onBindViewHolder(ViewHolder holder, int position){
        HotSearchBean item = mDatas.get(position);
        holder.searchWord.setText(item.getSearchWord());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
