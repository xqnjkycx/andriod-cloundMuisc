package com.example.fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.Adapter.SearchListAdapter;
import com.example.Adapter.UserCollectionSongListAdapter;
import com.example.bean.UserCollectionSongListBean;
import com.example.gsonClass.UserInfoClass;
import com.example.mycloudmusic.MainActivity;
import com.example.mycloudmusic.R;
import com.example.tools.HttpRequestTool;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;
import okhttp3.Call;
import okhttp3.Response;
import com.example.gsonClass.UserCollectionSongListGson;

public class userFragment extends Fragment {
    private String nickName;
    private String avatarUrl;
    private String backgroundUrl;
    private String level;
    private String fans;
    private String follows;
    private String listenSongs;

    private List<UserCollectionSongListBean> collectionSongList = new ArrayList<>();

    private long id;

    private Context fragmentContext;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.user_fragment,container,false);
        return v;
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentContext = getContext();
        initUserInfo();
        initUserCollectionSongList();
    }

    @Override
    public void onStart() {
        super.onStart();
        drawUserInfo();
    }
    //初始化用户基本信息数据
    private void initUserInfo(){
        Bundle bundle = userFragment.this.getArguments();
        nickName = bundle.getString("nickName");
        avatarUrl = bundle.getString("avatarUrl");
        backgroundUrl = bundle.getString("backgroundUrl");
        id = bundle.getLong("userId");
        String url = "http://10.0.2.2:3000/user/detail?uid="+id;
        HttpRequestTool.get(url,new okhttp3.Callback(){
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseData = response.body().string();
                Gson gson = new Gson();
                UserInfoClass userInfo = gson.fromJson(responseData,UserInfoClass.class);
                level = userInfo.getLevel()+"";
                listenSongs = userInfo.getListenSongs()+"";
                UserInfoClass.ProfileDTO obj = userInfo.getProfile();
                fans = obj.getFolloweds()+"";
                follows = obj.getFollows()+"";
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }
        });
    }
    //初始化用户收藏歌单数据
    private void initUserCollectionSongList(){
        String url = "http://10.0.2.2:3000/user/playlist?uid="+id;
        HttpRequestTool.get(url,new okhttp3.Callback(){
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String resData = response.body().string();
                Gson gson = new Gson();
                UserCollectionSongListGson res = gson.fromJson(resData,UserCollectionSongListGson.class);
                List<UserCollectionSongListGson.PlaylistDTO> playList
                        = res.getPlaylist();
                String coverImgUrl;
                int trackCount;
                String name;
                long id;
                String nickName;
                for(UserCollectionSongListGson.PlaylistDTO item : playList){
                    coverImgUrl = item.getCoverImgUrl();
                    trackCount = item.getTrackCount();
                    name = item.getName();
                    id = item.getId();
                    UserCollectionSongListGson.PlaylistDTO.CreatorDTO creators = item.getCreator();
                    nickName = creators.getNickname();
                    collectionSongList.add(new UserCollectionSongListBean(trackCount,coverImgUrl,nickName,name,id));
                }
                Message message = new Message();
                message.what = 2;
                handler.sendMessage(message);

            }
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }
        });
    }
    //绘制用户基本信息UI界面
    private void drawUserInfo(){
        ImageView avatar = (ImageView) getActivity().findViewById(R.id.userfragment_user_avatar);
        RelativeLayout userBg = (RelativeLayout) getActivity().findViewById(R.id.userfragment_user_bg);
        TextView userName = (TextView) getActivity().findViewById(R.id.userfragment_name);
        TextView userLevel = (TextView) getActivity().findViewById(R.id.user_level);
        TextView userFans = (TextView) getActivity().findViewById(R.id.user_fans);
        TextView userFllows = (TextView) getActivity().findViewById(R.id.user_follow);
        TextView userListenSongs = (TextView) getActivity().findViewById(R.id.user_listenSong);
        userName.setText(nickName);
        userLevel.setText(level);
        userFans.setText(fans);
        userFllows.setText(follows);
        userListenSongs.setText(listenSongs);

        Glide.with(this)
                .load(backgroundUrl)
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull @NotNull Drawable resource, @Nullable @org.jetbrains.annotations.Nullable Transition<? super Drawable> transition) {
                        userBg.setBackground(resource);
                    }
                    @Override
                    public void onLoadCleared(@Nullable @org.jetbrains.annotations.Nullable Drawable placeholder) {

                    }
                });
        Glide.with(this).load(avatarUrl).into(avatar);
    }
    //绘制用户收藏歌单界面
    private void drawUserCollectionSongList(){
//        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.search_list_recycler);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(fragmentContext);
//        recyclerView.setLayoutManager(layoutManager);
//        SearchListAdapter adapter = new SearchListAdapter(searchResList);
//        recyclerView.setAdapter(adapter);
        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.user_collection_songList_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(fragmentContext);
        recyclerView.setLayoutManager(layoutManager);
        UserCollectionSongListAdapter adapter = new UserCollectionSongListAdapter(collectionSongList);
        recyclerView.setAdapter(adapter);
    }
    //异步处理流程
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case 1:
                    drawUserInfo();
                    break;
                case 2:
                    drawUserCollectionSongList();
                default:
                    break;
            }
        }
    };
}