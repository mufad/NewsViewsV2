package com.mufadmonwar.newsviewsv2.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mufadmonwar.newsviewsv2.R;
import com.mufadmonwar.newsviewsv2.network.SearchApiClient;
import com.mufadmonwar.newsviewsv2.utils.AppConstants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchNumbersActivity extends BaseActivity {

    private Activity mActivity;
    private Context mContext;
    private String query;
    private TextView tvResponse;
    private ProgressBar pbLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVars();
        initViews();
        loadData();
    }

    private void initVars() {
        mActivity = SearchNumbersActivity.this;
        mContext = getApplicationContext();
        query = getIntent().getStringExtra(AppConstants.INTENT_KEY_QUERY);
    }

    private void initViews() {

        setContentView(R.layout.activity_search_numbers);
        initToolbar();
        enableBackButton();
        setToolbarTitle(getResources().getString(R.string.search));
        tvResponse=findViewById(R.id.tv_number_message);
        pbLoader=findViewById(R.id.pb_loader);
    }

    private void loadData(){
        SearchApiClient.getClient().getNumberInfo(query).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    tvResponse.setText(response.body());
                    pbLoader.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("TAG", t.toString());
            }
        });
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
