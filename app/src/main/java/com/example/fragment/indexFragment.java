package com.example.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.Adapter.SongListAdapter;
import com.example.bean.BannerBean;
import com.example.bean.SongListBean;
import com.example.mycloudmusic.R;
import com.example.tools.HttpRequestTool;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import okhttp3.Response;
import com.example.gsonClass.bannerClass;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.example.gsonClass.personlizedListSongClass;

import org.jetbrains.annotations.NotNull;


public class indexFragment extends Fragment  {
    private Context fragmentContext;
    private Banner banner;
    //轮播图数据List
    private List<BannerBean> pics =  new ArrayList<>() ;
    //歌单数据List
    private List<SongListBean> songList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.index_fragment,container,false);
        return v;
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentContext = getContext();
        Log.d("fragment上下文",fragmentContext+"");
        initBannerData();
        //初始化推荐歌单
        initPersonalized();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        banner.destroy();
    }
    //请求并整理轮播图banner的网络数据
    private void initBannerData(){
        String url =  "http://10.0.2.2:3000/banner?type=1";
        HttpRequestTool.get(url,new okhttp3.Callback(){
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                Gson gson = new Gson();
                bannerClass banners = gson.fromJson(responseData,bannerClass.class);
                List<bannerClass.BannersDTO> bannersDTOList = banners.getBanners();
                for (bannerClass.BannersDTO bannerItem : bannersDTOList){
                    pics.add(new BannerBean(bannerItem.getPic()));
                    Message message = new Message();
                    message.what = 2;
                    handler.sendMessage(message);
                }
            }
            @Override
            public void  onFailure(Call call,IOException e){
            }
        });
    }
    //请求并处理推荐歌单的网络数据
    private void initPersonalized(){
        String url = "http://10.0.2.2:3000/personalized?limit=8";
        HttpRequestTool.get(url,new okhttp3.Callback(){
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseData = response.body().string();
                Gson gson = new Gson();
                personlizedListSongClass personalized = gson.fromJson(responseData,personlizedListSongClass.class);
                List<personlizedListSongClass.ResultDTO> personalizedSongList = personalized.getResult();
                for (personlizedListSongClass.ResultDTO item:personalizedSongList){
                    songList.add(new SongListBean(item.getName(),item.getPicUrl()));
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
    //绘制personalizedUI
    private void drawPersonalized(){
        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.personalized_songlist_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(fragmentContext);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        SongListAdapter adapter = new SongListAdapter(songList);
        recyclerView.setAdapter(adapter);
    }
    //绘制bannerUI
    public void drawBanner(){
        //初始化banner
        banner = (Banner) getActivity().findViewById(R.id.banner);
        banner.setAdapter(new BannerImageAdapter<BannerBean>(pics) {
            public void onBindView(BannerImageHolder holder, BannerBean data, int position, int size) {
                Glide.with(holder.imageView)
                        .load(data.getUrl())
                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                        .into(holder.imageView);
            }
        }).addBannerLifecycleObserver(this).setIndicator(new CircleIndicator(fragmentContext));
        banner.start();
    }
    //切回主线程进行UI操作
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case 1:
                    drawPersonalized();
                    break;
                case 2:
                    drawBanner();
                    break;
                default:
                    break;
            }
        }
    };
}
