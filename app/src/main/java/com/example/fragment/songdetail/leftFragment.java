package com.example.fragment.songdetail;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.mycloudmusic.MainActivity;
import com.example.mycloudmusic.R;
import com.example.mycloudmusic.SongDetailActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class leftFragment extends Fragment {
    private CircleImageView CDView;
    private String CD;
    private ObjectAnimator CDAnimation;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_left,container,false);
        return view;
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        CDView = (CircleImageView) getActivity().findViewById(R.id.CD);
        //设置CD旋转的属性
        CDAnimation = ObjectAnimator.ofFloat(CDView,View.ROTATION,0,360);
        CDAnimation.setDuration(10000)
                    .setRepeatCount(ObjectAnimator.INFINITE);
        CDAnimation.setInterpolator(new LinearInterpolator());
//        CDAnimation.setRepeatMode(ObjectAnimator.RESTART);
    }
    @Override
    public void onResume() {
        super.onResume();
        SongDetailActivity activity = (SongDetailActivity) getActivity();
        activity.drawCD();
        playCD();
    }
    public void drawCD(String url){
        Glide.with(this).load(url).into(CDView);
    }
    //CD转动方法
    public void playCD(){
        CDAnimation.start();
    }
    //CD保持状态运行
    public void resumeCD(){
        CDAnimation.resume();
    }
    //CD暂停方法
    public void pauseCD(){
        CDAnimation.pause();
    }
}
