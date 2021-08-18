package com.example.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bean.SongListBean;
import com.example.bean.UserCollectionSongListBean;
import com.example.mycloudmusic.R;
import com.example.mycloudmusic.SongListDetailActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UserCollectionSongListAdapter extends RecyclerView.Adapter<UserCollectionSongListAdapter.ViewHolder> {
    private List<UserCollectionSongListBean> mDatas;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgView;
        View songListView;
        TextView name;
        TextView description;
        public ViewHolder(View view){
            super(view);
            songListView = view;
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
        final ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                UserCollectionSongListBean songList = mDatas.get(position);
                String picUrl = songList.getCoverImgUrl();
                String name = songList.getName();
                long id = songList.getId();
                String cookie = songList.getCookie();
                Intent intent = new Intent(parent.getContext(), SongListDetailActivity.class);
                intent.putExtra("picUrl",picUrl);
                intent.putExtra("name",name);
                intent.putExtra("id",id);
                intent.putExtra("cookie",cookie);
                parent.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserCollectionSongListBean item = mDatas.get(position);
        Glide.with(holder.itemView)
                .load(item.getCoverImgUrl())
                .into(holder.imgView);
        holder.name.setText(item.getName());
        String des = item.getTrackCount()+"首, by " +item.getNickName();
        holder.description.setText(des);
    }
    public int getItemCount(){
        return mDatas.size();
    }
}
