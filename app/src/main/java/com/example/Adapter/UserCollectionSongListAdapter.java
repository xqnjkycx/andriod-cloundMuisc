package com.example.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bean.UserCollectionSongListBean;
import com.example.mycloudmusic.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UserCollectionSongListAdapter extends RecyclerView.Adapter<UserCollectionSongListAdapter.ViewHolder> {
    private List<UserCollectionSongListBean> mDatas;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgView;
        TextView name;
        TextView description;
        public ViewHolder(View view){
            super(view);
            imgView = (ImageView) view.findViewById(R.id.user_collection_item_coverImg);
            name = (TextView) view.findViewById(R.id.user_collecton_songList_name);
            description = (TextView) view.findViewById(R.id.user_collection_songList_description);
        }
    }
    public UserCollectionSongListAdapter(List<UserCollectionSongListBean> list){
        mDatas = list;
    }
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_collection_songlist_item_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserCollectionSongListBean item = mDatas.get(position);
        Glide.with(holder.itemView)
                .load(item.getCoverImgUrl())
                .into(holder.imgView);
        holder.name.setText(item.getName());
        String des = item.getTrackCount()+", by " +item.getNickName();
        holder.description.setText(des);
    }
    public int getItemCount(){
        return mDatas.size();
    }
}
