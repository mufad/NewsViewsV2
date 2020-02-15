package com.mufadmonwar.newsviewsv2.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.mufadmonwar.newsviewsv2.R;
import com.mufadmonwar.newsviewsv2.activities.MainActivity;
import com.mufadmonwar.newsviewsv2.preferences.AppPreference;
import com.mufadmonwar.newsviewsv2.preferences.PrefKey;


public class ContinueFragment extends Fragment {


    private ImageView ivNext;

    public ContinueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_continue, container, false);
        ivNext=view.findViewById(R.id.thumb_next);
        initListener();
        return view;

    }


    private void initListener(){
        ivNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Continue", Toast.LENGTH_SHORT).show();
                AppPreference.getInstance(getContext()).setBoolean(PrefKey.DONE, true);
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
            }
        });
    }


}
