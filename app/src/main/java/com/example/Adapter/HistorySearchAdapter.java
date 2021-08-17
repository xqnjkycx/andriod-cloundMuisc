package com.example.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.LitePal.HistorySearch;
import com.example.bean.HistorySearchBean;
import com.example.mycloudmusic.R;

import java.util.List;

public class HistorySearchAdapter extends RecyclerView.Adapter<HistorySearchAdapter.ViewHolder> {
    private List<HistorySearchBean> mDatas;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View itemView;
        TextView historySearchName;
        public ViewHolder(View v){
            super(v);
            itemView = v;
            historySearchName = (TextView) v.findViewById(R.id.histroy_search_textview);
        }
    }
    public HistorySearchAdapter(List<HistorySearchBean> list){
        mDatas = list;
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_search_item_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("我被点击了！！！","11111");
            }
        });
        return holder;
    }
    public void onBindViewHolder(ViewHolder holder, int position){
        HistorySearchBean item = mDatas.get(position);
        holder.historySearchName.setText(item.getHistorySearchName());
    }
    public int getItemCount(){
        return mDatas.size();
    }
}
