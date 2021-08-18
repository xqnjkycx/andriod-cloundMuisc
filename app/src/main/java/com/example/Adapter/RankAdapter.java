package com.example.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bean.RankBean;
import com.example.bean.UserCollectionSongListBean;
import com.example.mycloudmusic.R;
import com.example.mycloudmusic.SongListDetailActivity;

import java.util.List;

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.ViewHolder> {
    private List<RankBean> mDatas;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View rankView;
        ImageView rankCoverImg;
        TextView rankName;
        TextView rankUpdate;
        public ViewHolder(View view){
            super(view);
            rankView = view;
            rankCoverImg = (ImageView) view.findViewById(R.id.rank_item_coverImage);
            rankName = (TextView) view.findViewById(R.id.rank_item_name);
            rankUpdate = (TextView) view.findViewById(R.id.rank_item_update);
        }
    }
    public RankAdapter(List<RankBean> list){
        mDatas = list;
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rank_fragment_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                RankBean rankList = mDatas.get(position);
                String coverImgUrl = rankList.getCoverImgUrl();
                String name = rankList.getName();
                long id = rankList.getId();
                String cookie = rankList.getCookie();
                Intent intent = new Intent(parent.getContext(),SongListDetailActivity.class);
                intent.putExtra("picUrl",coverImgUrl);
                intent.putExtra("name",name);
                intent.putExtra("id",id);
                intent.putExtra("cookie",cookie);
                parent.getContext().startActivity(intent);
            }
        });
        return holder;
    }
    public void onBindViewHolder(ViewHolder holder,int position){
        RankBean item = mDatas.get(position);
        Glide.with(holder.itemView).load(item.getCoverImgUrl()).into(holder.rankCoverImg);
        holder.rankName.setText(item.getName());
        holder.rankUpdate.setText(item.getUpdateFrequency());
    }
    public int getItemCount(){
        return mDatas.size();
    }
}
