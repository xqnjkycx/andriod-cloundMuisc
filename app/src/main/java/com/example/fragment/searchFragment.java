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
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.Adapter.HistorySearchAdapter;
import com.example.Adapter.HotPopularAdapter;
import com.example.LitePal.HistorySearch;
import com.example.bean.HistorySearchBean;
import com.example.bean.HotSearchBean;
import com.example.bean.SearchListBean;
import com.example.gsonClass.HotSearchClass;
import com.example.gsonClass.SearchListClass;
import com.example.mycloudmusic.R;
import com.example.tools.HttpRequestTool;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

import com.example.Adapter.HotSearchAdapter;
import com.example.Adapter.SearchListAdapter;

public class searchFragment extends Fragment {
    private Context fragmentContext;
    private List<HotSearchBean> hotSearchList =  new ArrayList<>() ;
    private List<SearchListBean> searchResList = new ArrayList<>();
//    private RecyclerView hotSearchRecyclerView;
    private RecyclerView searchListRecyclerView;
    private View hotAndHistoryView;
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
        hotAndHistoryView = (View) getActivity().findViewById(R.id.hot_and_history_view);
        searchListRecyclerView = (RecyclerView) getActivity().findViewById(R.id.search_list_recycler);
        hotAndHistoryView.setVisibility(View.VISIBLE);
        searchListRecyclerView.setVisibility(View.GONE);
        drawHistroySearch();
        initSearchInput();
        iniClearHistory();
    }

    private void iniClearHistory(){
        TextView clearHistory = (TextView) getActivity().findViewById(R.id.clear_history);
        clearHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.deleteAll(HistorySearch.class);
                drawHistroySearch();
            }
        });
    }

    private void initSearchInput(){
        EditText input = (EditText) getActivity().findViewById(R.id.searchInput);
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() == 0){
                    /**
                     * ???????????????????????????????????????0???????????????????????????????????????????????????????????????????????????
                     * ??????????????????searchListFragment
                     */
                    hotAndHistoryView.setVisibility(View.VISIBLE);
                    searchListRecyclerView.setVisibility(View.GONE);

                }else {
                    /**
                     * ?????????????????????????????????????????????????????????????????????????????????????????????
                     * */
                    String url = "http://10.0.2.2:3000/search?keywords="+s.toString();
                    initSearchList(url);
                    hotAndHistoryView.setVisibility(View.GONE);
                    searchListRecyclerView.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    //?????????????????????????????????
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
    //???????????????????????????
    private void initSearchList(String url){
        HttpRequestTool.get(url,new okhttp3.Callback(){
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseData = response.body().string();
                Gson gson = new Gson();
                SearchListClass obj = gson.fromJson(responseData,SearchListClass.class);
                SearchListClass.ResultDTO searchList = obj.getResult();
                List<SearchListClass.ResultDTO.SongsDTO> songs = searchList.getSongs();
                searchResList.clear();
                for(SearchListClass.ResultDTO.SongsDTO item : songs){
                    String songName = item.getName();
                    long id = item.getId();
                    String authorName = "";
                    List<SearchListClass.ResultDTO.SongsDTO.ArtistsDTO> aritis = item.getArtists();
                    for (SearchListClass.ResultDTO.SongsDTO.ArtistsDTO item1 : aritis){
                        if (authorName.length() == 0){
                            authorName = item1.getName();
                        }else {
                            authorName = authorName +" & "+ item1.getName();
                        }
                    }
                    searchResList.add(new SearchListBean(songName,authorName,id));
                    Message message = new Message();
                    message.what = 2;
                    handler.sendMessage(message);
                }
            }
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }
        });
    }
    //??????????????????
    private void  drawHotSearch(){
        RecyclerView recyclerView = (RecyclerView) getActivity()
                .findViewById(R.id.hot_search_detail_recycler_view);
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        HotSearchAdapter adapter = new HotSearchAdapter(hotSearchList);
        recyclerView.setAdapter(adapter);

    }
    //????????????????????????
    private void drawSearchList(){
        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.search_list_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(fragmentContext);
        recyclerView.setLayoutManager(layoutManager);
        SearchListAdapter adapter = new SearchListAdapter(searchResList);
        recyclerView.setAdapter(adapter);
    }
    //????????????????????????
    private void drawHistroySearch(){
        List<HistorySearch> list = LitePal.findAll(HistorySearch.class);
        List<HistorySearchBean> history = new ArrayList<>();
        for(HistorySearch item:list){
            history.add(new HistorySearchBean(item.getHistorySearchName()));
        }
        RecyclerView recyclerView = (RecyclerView) getActivity()
                .findViewById(R.id.history_search_recycler_view);
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        HistorySearchAdapter adapter = new HistorySearchAdapter(history);
        recyclerView.setAdapter(adapter);
    }
    //??????????????????
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case 1:
                    drawHotSearch();
                    break;
                case 2:
                    drawSearchList();
                default:
                    break;
            }
        }
    };
}