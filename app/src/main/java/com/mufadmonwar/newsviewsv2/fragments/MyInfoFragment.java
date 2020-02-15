package com.mufadmonwar.newsviewsv2.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mufadmonwar.newsviewsv2.R;
import com.mufadmonwar.newsviewsv2.utils.AppConstants;


public class MyInfoFragment extends Fragment {


    private ImageView ivProfile;
    public MyInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_info, container, false);
        ivProfile=view.findViewById(R.id.profile_image);
        Glide.with(getActivity()).load(AppConstants.PROFILE_IMAGE).into(ivProfile);
        return inflater.inflate(R.layout.fragment_my_info, container, false);
    }

}
