package com.example.Adapter;

import android.graphics.Color;
import android.util.TypedValue;
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
    private String selectedLyric = "";
    private TextView lastSelectedTextView;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView lyric;
        public ViewHolder(View view){
            super(view);
            lyric = (TextView) view.findViewById(R.id.lyric_item);
        }
    }
    //歌词界面初始化构造器
    public LyricAdapter(List<lyricBean> list ){
        mDatas = list;
    }
    public void selectedItem(int position,String selectedLyric){
        this.selectedLyric = selectedLyric;
        notifyItemChanged(position);
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
        if(item.getLyric().equals(selectedLyric)){
            if(lastSelectedTextView!=null && lastSelectedTextView != holder.lyric){
                lastSelectedTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP,13);
                lastSelectedTextView.setTextColor(Color.parseColor("#E0E0E0"));
            }
            holder.lyric.setTextColor(Color.parseColor("#FFFFFF"));
            holder.lyric.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
            lastSelectedTextView = holder.lyric;
        }else {
            holder.lyric.setTextColor(Color.parseColor("#E0E0E0"));
            holder.lyric.setTextSize(TypedValue.COMPLEX_UNIT_SP,13);
        }
    }
    public int getItemCount(){
        return mDatas.size();
    }
}

