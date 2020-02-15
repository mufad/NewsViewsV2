package com.mufadmonwar.newsviewsv2.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.mufadmonwar.newsviewsv2.R;
import com.mufadmonwar.newsviewsv2.preferences.AppPreference;
import com.mufadmonwar.newsviewsv2.preferences.PrefKey;
import com.mufadmonwar.newsviewsv2.utils.ActivityUtils;

public class SplashActivity extends AppCompatActivity {
    private Activity mActivity;
    private Context mContext;

    private static final int SPLASH_DURATION = 1500;

    private ImageView ivSplashImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initVars();
        initViews();
        initFunctionality();
    }

    private void initVars() {
        mActivity = SplashActivity.this;
        mContext = mActivity.getApplicationContext();
    }

    private void initViews() {
        setContentView(R.layout.activity_splash);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ivSplashImage = findViewById(R.id.iv_splash);

    }
    private void initFunctionality() {
        ivSplashImage.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (AppPreference.getInstance(mContext).getBoolean(PrefKey.DONE)){

                    ActivityUtils.getInstance()
                            .invokeActivity(mActivity, MainActivity.class, true);
                }else{

                    ActivityUtils.getInstance()
                            .invokeActivity(mActivity, IntroActivity.class, true);
                }

            }


        }, SPLASH_DURATION);


    }

}
