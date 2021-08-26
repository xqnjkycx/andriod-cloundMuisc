//歌单详情页面的适配器
package com.example.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.Service.MusicPlayerService;
import com.example.bean.MusicInfo;
import com.example.bean.personalizedDetailBean;
import com.example.mycloudmusic.R;
import com.example.mycloudmusic.SongDetailActivity;

import java.util.List;

public class SongListDetailAdapter extends RecyclerView.Adapter<SongListDetailAdapter.ViewHolder> {
    private List<personalizedDetailBean> mDatas;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View songView;
        TextView songName;
        TextView authorName;
        public ViewHolder(View v){
            super(v);
            songView = v;
            songName = (TextView) v.findViewById(R.id.detail_songName);
            authorName = (TextView) v.findViewById(R.id.detail_authorName);
        }
    }
    public SongListDetailAdapter(List<personalizedDetailBean> list){
        mDatas = list;
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.songlist_detail_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        holder.songView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                personalizedDetailBean item = mDatas.get(position);
                long id = item.getId();
                String songName = item.getSongName();
                String authorName = item.getAuthorName();
                String bgImg = item.getSongImg();
                Intent intent = new Intent(parent.getContext(), SongDetailActivity.class);
                intent.putExtra("id",id).putExtra("songName",songName)
                        .putExtra("authorName",authorName).putExtra("bgImg",bgImg);
                Boolean flag = true;
                for(int i = 0 ; i < MusicPlayerService.getMusicPlayList().size();i++){
                    MusicInfo info = MusicPlayerService.getMusicPlayList().get(i);
                    if(id == info.getId()){
                        //说明已经存在这首歌在列表里面了
                        flag = false;
                        MusicPlayerService.setPlayIndex(i);
                    }
                }
                if(flag == true){
                    //说明新增的这首歌还没在列表里面
                    MusicPlayerService.setPlayIndex(MusicPlayerService.getMusicPlayList().size());
                }
                parent.getContext().startActivity(intent);
            }
        });
        return holder;
    }
    public void onBindViewHolder(ViewHolder holder, int position){
        personalizedDetailBean detail = mDatas.get(position);
        holder.authorName.setText("作者: "+detail.getAuthorName());
        holder.songName.setText(detail.getSongName());
    }
    public int getItemCount(){
        return mDatas.size();
    }
}
