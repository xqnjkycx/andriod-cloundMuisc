package com.example.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Adapter.RankAdapter;
import com.example.bean.RankBean;
import com.example.gsonClass.RankGson;
import com.example.mycloudmusic.R;
import com.example.tools.HttpRequestTool;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class rankFragment extends Fragment {
    private Context fragmentContext;
    private String cookie;
    private List<RankBean> rankList = new ArrayList<>();
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.rank_fragment,container,false);
        return v;
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentContext = getContext();
        initRankData();
    }
    //请求歌单榜的数据
    private void initRankData(){
        Bundle bundle = rankFragment.this.getArguments();
        cookie = bundle.getString("cookie");
        Log.d("cookie",""+cookie);
        String url = "http://10.0.2.2:3000/toplist";
        HttpRequestTool.get(url,new okhttp3.Callback(){
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseData = response.body().string();
                Gson gson = new Gson();
                RankGson rank = gson.fromJson(responseData,RankGson.class);
                List<RankGson.ListDTO> list = rank.getList();
                for(RankGson.ListDTO item : list){
                    rankList.add(new RankBean(item.getUpdateFrequency(),
                            item.getDescription(),
                            item.getCoverImgUrl(),
                            item.getName(),
                            item.getId(),cookie));
                }
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }
        });
    }
    //绘制排行榜UI界面
    private void drawRank(){
        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.rank_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(fragmentContext);
        recyclerView.setLayoutManager(layoutManager);
        RankAdapter adapter = new RankAdapter(rankList);
        recyclerView.setAdapter(adapter);

    }
    //异步处理机制流程
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case 1:
                    drawRank();
                    break;

                default:
                    break;
            }
        }
    };
}