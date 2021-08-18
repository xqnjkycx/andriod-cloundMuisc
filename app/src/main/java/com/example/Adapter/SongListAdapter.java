//推荐歌单的适配器
package com.example.Adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.mycloudmusic.MainActivity;
import com.example.mycloudmusic.R;
import com.example.mycloudmusic.SongListDetailActivity;

import java.util.List;

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.ViewHolder> {
    private List<SongListBean> mDatas;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View songListView;
        ImageView songListImage;
        TextView songListName;
        public ViewHolder(View view){
            super(view);
            songListView = view;
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
        holder.songListView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                SongListBean songList = mDatas.get(position);
                String picUrl = songList.getSongListPics();
                String name = songList.getSongListName();
                long id = songList.getSongListId();
                Intent intent = new Intent(parent.getContext(), SongListDetailActivity.class);
                intent.putExtra("picUrl",picUrl);
                intent.putExtra("name",name);
                intent.putExtra("id",id);
                parent.getContext().startActivity(intent);
            }
        });
        return holder;
    }
    public void onBindViewHolder(ViewHolder holder,int position){
        SongListBean item = mDatas.get(position);
        Glide.with(holder.itemView)
                .load(item.getSongListPics())
                .into(holder.songListImage);
       holder.songListName.setText(item.getSongListName());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
