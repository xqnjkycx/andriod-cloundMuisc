//歌单详情页面的适配器
package com.example.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.example.bean.personalizedDetailBean;
import com.example.mycloudmusic.R;
import java.util.List;

public class SongListDetailAdapter extends RecyclerView.Adapter<SongListDetailAdapter.ViewHolder> {
    private List<personalizedDetailBean> mDatas;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView songName;
        TextView authorName;
        public ViewHolder(View v){
            super(v);
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
