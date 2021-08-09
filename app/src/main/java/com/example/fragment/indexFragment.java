package com.example.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.bean.BannerBean;
import com.example.mycloudmusic.MainActivity;
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


public class indexFragment extends Fragment  {
    private List<BannerBean> pics =  new ArrayList<>() ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.index_fragment,container,false);
        initData();
        return v;
    }
    private void initData(){
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
                }
            }
            @Override
            public void  onFailure(Call call,IOException e){
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        useBanner();
    }

    public void useBanner(){
        Banner banner = (Banner) getActivity().findViewById(R.id.banner);
        banner.setAdapter(new BannerImageAdapter<BannerBean>(pics) {
            public void onBindView(BannerImageHolder holder, BannerBean data, int position, int size) {
                Glide.with(getContext()).load(data.getUrl()).apply(RequestOptions.bitmapTransform(new RoundedCorners(300))).into(holder.imageView);
            }

        }).addBannerLifecycleObserver(this);
    }
}
