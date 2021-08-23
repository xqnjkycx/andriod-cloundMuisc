package com.example.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bean.lyricBean;
import com.example.mycloudmusic.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LyricAdapter extends RecyclerView.Adapter<LyricAdapter.ViewHolder> {
    private List<lyricBean> mDatas;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView lyric;
        public ViewHolder(View view){
            super(view);
            lyric = (TextView) view.findViewById(R.id.lyric_item);
        }
    }
    public LyricAdapter(List<lyricBean> list ){
        mDatas = list;
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lyric_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public void onBindViewHolder(ViewHolder holder, int position){
        lyricBean item = mDatas.get(position);
        holder.lyric.setText(item.getLyric());
    }
    public int getItemCount(){
        return mDatas.size();
    }
}
