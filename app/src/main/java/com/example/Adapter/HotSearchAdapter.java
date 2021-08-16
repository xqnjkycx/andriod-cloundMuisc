package com.example.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bean.HotSearchBean;
import com.example.mycloudmusic.R;

import java.util.List;

public class HotSearchAdapter extends RecyclerView.Adapter<HotSearchAdapter.ViewHolder> {
    private List<HotSearchBean> mDatas;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView searchWord;
        public ViewHolder(View v){
            super(v);
            searchWord = (TextView) v.findViewById(R.id.hot_search_textview);
            Log.d("空指针异常","searchWord"+searchWord);
        }
    }
    public HotSearchAdapter(List<HotSearchBean> list){
        mDatas = list;
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hot_search_item,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }
    public void onBindViewHolder(ViewHolder holder, int position){
        HotSearchBean item = mDatas.get(position);
        holder.searchWord.setText(item.getSearchWord());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
