package com.example.fragment.songdetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.mycloudmusic.R;
import com.example.mycloudmusic.SongDetailActivity;

public class rightFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_right,container,false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        SongDetailActivity activity = (SongDetailActivity) getActivity();
        activity.getRightFragmentInstance();
    }
}
