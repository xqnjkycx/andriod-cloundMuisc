package com.example.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.bean.HotSearchBean;
import com.example.gsonClass.HotSearchClass;
import com.example.mycloudmusic.R;
import com.example.tools.HttpRequestTool;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

import com.example.Adapter.HotSearchAdapter;

public class searchFragment extends Fragment {
    private Context fragmentContext;
    private List<HotSearchBean> hotSearchList =  new ArrayList<>() ;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.search_fragment,container,false);
        return v;
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentContext = getContext();
        initHotSearch();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void initSearchInput(){
        EditText input = (EditText) getActivity().findViewById(R.id.input);
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private void initHotSearch(){
        String url = "http://10.0.2.2:3000/search/hot/detail";
        HttpRequestTool.get(url,new okhttp3.Callback(){
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseData = response.body().string();
                Gson gson = new Gson();
                HotSearchClass obj = gson.fromJson(responseData,HotSearchClass.class);
                List<HotSearchClass.DataDTO> hotSearch = obj.getData();
                for(HotSearchClass.DataDTO item : hotSearch ){
                    String searchWord = item.getSearchWord();
                    hotSearchList.add(new HotSearchBean(searchWord));
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                }
            }
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }
        });
    }
    //绘制热搜列表
    private void  drawHotSearch(){
        RecyclerView recyclerView = (RecyclerView) getActivity()
                .findViewById(R.id.hot_search_detail_recycler_view);
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        HotSearchAdapter adapter = new HotSearchAdapter(hotSearchList);
        recyclerView.setAdapter(adapter);

    }
    //异步处理机制
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case 1:
                    drawHotSearch();
                    break;
                default:
                    break;
            }
        }
    };
}