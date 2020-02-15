package com.mufadmonwar.newsviewsv2.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.navigation.NavigationView;
import com.mufadmonwar.newsviewsv2.BuildConfig;
import com.mufadmonwar.newsviewsv2.R;
import com.mufadmonwar.newsviewsv2.adapter.NewsSourcesAdapter;
import com.mufadmonwar.newsviewsv2.adapter.TopHeadlineAdapter;
import com.mufadmonwar.newsviewsv2.model.headlines.Article;
import com.mufadmonwar.newsviewsv2.model.headlines.TopNewsResponse;
import com.mufadmonwar.newsviewsv2.model.news_source.NewsSourcesResponse;
import com.mufadmonwar.newsviewsv2.model.news_source.Source;
import com.mufadmonwar.newsviewsv2.network.NewsApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener  {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;

    private Activity mActivity;
    private Context mContext;
    private View navHeaderView;

    private RecyclerView rvSources, rvHeadlines;
    private NewsSourcesAdapter newsSourcesAdapter;
    private TopHeadlineAdapter topHeadlineAdapter;
    private ArrayList<Source> sourceArrayList;

    private ArrayList<Article>articleArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVars();
        initViews();
        initFunctionality();
        loadData();
    }
    private void initVars() {
        mActivity = MainActivity.this;
        mContext = mActivity.getApplicationContext();
        sourceArrayList=new ArrayList<>();
        articleArrayList=new ArrayList<>();
    }

    private void initViews() {
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout_lawyer);
        navigationView = findViewById(R.id.navigation_view);
        rvSources=findViewById(R.id.rv_sources);
        rvHeadlines=findViewById(R.id.rv_headline);
        initToolbar();
        enableBackButton();

        setToolbarTitle(getString(R.string.app_name));
        initDrawer();
    }

    private void initDrawer() {
        drawerToggle = new ActionBarDrawerToggle(mActivity, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navHeaderView = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        if (drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadData(){
        NewsApiClient.getClient().getNewsSources("en", "us",
                BuildConfig.ApiKey).enqueue(new Callback<NewsSourcesResponse>() {
            @Override
            public void onResponse(Call<NewsSourcesResponse> call, Response<NewsSourcesResponse> response) {
                if (response.isSuccessful()){
                    Log.d("TAG", response.body().getSources().size()+"");
                    sourceArrayList.clear();
                    sourceArrayList.addAll(response.body().getSources());
                    newsSourcesAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<NewsSourcesResponse> call, Throwable t) {
                Log.d("TAG", t.toString());

            }
        });


        NewsApiClient.getClient().getTopNews("abc-news",
                BuildConfig.ApiKey).enqueue(new Callback<TopNewsResponse>() {
            @Override
            public void onResponse(Call<TopNewsResponse> call, Response<TopNewsResponse> response) {
                if (response.isSuccessful()){
                    Log.d("TAG", response.body().getArticles().size()+"");

                    articleArrayList.clear();
                    articleArrayList.addAll(response.body().getArticles());
                    topHeadlineAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<TopNewsResponse> call, Throwable t) {

                Log.d("TAG", t.toString());
            }
        });
    }

    private void initFunctionality(){

        newsSourcesAdapter=new NewsSourcesAdapter(mContext, sourceArrayList);
        rvSources.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
        rvSources.setAdapter(newsSourcesAdapter);

        topHeadlineAdapter = new TopHeadlineAdapter(mContext, articleArrayList);
        rvHeadlines.setLayoutManager(new GridLayoutManager(mContext, 2));
        rvHeadlines.setAdapter(topHeadlineAdapter);

    }
}
