package com.example.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bean.SongListBean;
import com.example.mycloudmusic.R;

import java.util.List;

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.ViewHolder> {
    private List<SongListBean> mDatas;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView songListImage;
        TextView songListName;
        public ViewHolder(View view){
            super(view);
            songListImage = (ImageView) view.findViewById(R.id.songlist_item_image);
            songListName = (TextView) view.findViewById(R.id.songlist_item_name);
        }
    }
    public SongListAdapter(List<SongListBean> list){
        mDatas = list;
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.songlist_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    public void onBindViewHolder(ViewHolder holder,int position){
        SongListBean item = mDatas.get(position);
        holder.songListName.setText(item.getSongListName());
        Glide.with(holder.itemView)
                .load(item.getSongListPics())
                .into(holder.songListImage);
        Log.d("执行了吗", "onBindViewHolder: ");
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
