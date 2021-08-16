package com.example.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bean.SearchListBean;
import com.example.mycloudmusic.R;

import java.util.List;

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.ViewHolder> {
    private List<SearchListBean> mDatas;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView itemName;
        public ViewHolder(View view){
            super(view);
            itemName = (TextView) view.findViewById(R.id.search_list_item_text);
        }
    }
    public SearchListAdapter(List<SearchListBean> list){
        mDatas = list;
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_list_fragment_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    public void onBindViewHolder(ViewHolder holder,int position){
        SearchListBean item = mDatas.get(position);
        String str = item.getSongName()+" -- " + item.getAuthorName();
        holder.itemName.setText(str);
    }
    public int getItemCount(){
        return mDatas.size();
    }
}
