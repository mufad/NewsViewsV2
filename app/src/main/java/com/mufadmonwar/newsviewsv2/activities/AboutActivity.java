package com.mufadmonwar.newsviewsv2.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.mufadmonwar.newsviewsv2.BuildConfig;
import com.mufadmonwar.newsviewsv2.R;

public class AboutActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        initToolbar();
        setToolbarTitle(getString(R.string.about_title));
        enableBackButton();

        TextView tvVersion= findViewById(R.id.tv_version);
        tvVersion.setText(String.format("Version :%s", BuildConfig.VERSION_NAME));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
