package com.example.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.LitePal.HistorySearch;
import com.example.bean.HistorySearchBean;
import com.example.bean.SearchListBean;
import com.example.mycloudmusic.R;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.ViewHolder> {
    private List<SearchListBean> mDatas;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View itemView;
        TextView itemName;
        public ViewHolder(View view){
            super(view);
            itemView = view;
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input = (EditText) ((View) parent.getParent())
                        .findViewById(R.id.searchInput);
                String historySearchName = input.getText().toString();
                HistorySearch obj = new HistorySearch();
                obj.setHistorySearchName(historySearchName);
                obj.save();
                List<HistorySearch> list = LitePal.findAll(HistorySearch.class);
                List<HistorySearchBean> history = new ArrayList<>();
                for(HistorySearch item : list){
                    history.add(new HistorySearchBean(item.getHistorySearchName()));
                }
                RecyclerView recyclerView = (RecyclerView) ((View) parent.getParent())
                        .findViewById(R.id.history_search_recycler_view);
                StaggeredGridLayoutManager layoutManager = new
                        StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(layoutManager);
                HistorySearchAdapter adapter = new HistorySearchAdapter(history);
                recyclerView.setAdapter(adapter);
            }
        });
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
