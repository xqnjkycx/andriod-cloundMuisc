package com.example.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bean.HotPopularBean;
import com.example.bean.SongListBean;
import com.example.mycloudmusic.R;

import java.util.List;

public class HotPopularAdapter extends RecyclerView.Adapter<HotPopularAdapter.ViewHolder> {
    private List<HotPopularBean> mDatas;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView songListImage;
        TextView songListName;
        public ViewHolder(View view){
            super(view);
            songListImage = (ImageView) view.findViewById(R.id.songlist_item_image);
            songListName = (TextView) view.findViewById(R.id.songlist_item_name);
        }
    }
    public HotPopularAdapter(List<HotPopularBean> list){
        mDatas = list;
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.songlist_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    public void onBindViewHolder(ViewHolder holder,int position){
        HotPopularBean item = mDatas.get(position);
        Glide.with(holder.itemView)
                .load(item.getPicUrl())
                .into(holder.songListImage);
        holder.songListName.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}