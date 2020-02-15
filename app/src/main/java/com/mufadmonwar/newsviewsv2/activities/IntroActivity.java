package com.mufadmonwar.newsviewsv2.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.mufadmonwar.newsviewsv2.R;
import com.mufadmonwar.newsviewsv2.adapter.PageAdapter;

public class IntroActivity extends AppCompatActivity {


    private Activity mActivity;
    private Context mContext;

    private ViewPager viewPager;
    private PageAdapter pageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initVars();
        initViews();
        initFunctionalities();

    }
    private void initVars() {
        mActivity = IntroActivity.this;
        mContext = mActivity.getApplicationContext();
    }


    private void initViews(){
        setContentView(R.layout.activity_intro);
        viewPager=findViewById(R.id.viewPager);
    }

    private void initFunctionalities(){

        pageAdapter = new PageAdapter(getSupportFragmentManager(), 3);
        viewPager.setAdapter(pageAdapter);

    }
}
