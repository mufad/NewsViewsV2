package com.mufadmonwar.newsviewsv2.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mufadmonwar.newsviewsv2.R;
import com.mufadmonwar.newsviewsv2.model.headlines.Article;
import com.mufadmonwar.newsviewsv2.utils.AppConstants;

public class NewsDetailsActivity extends BaseActivity {

    private Activity mActivity;
    private Context mContext;

    private TextView tvTitle, tvDate, tvDetails;
    private ImageView ivThumb;
    private Article article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVars();
        initViews();
        loadData();
    }

    private void initVars(){
        mActivity=NewsDetailsActivity.this;
        mContext=getApplicationContext();
        article= (Article) getIntent().getSerializableExtra(AppConstants.INTENT_KEY_ARTICLE);
    }

    private void initViews(){

        setContentView(R.layout.activity_news_details);

        initToolbar();
        enableBackButton();
        setToolbarTitle("News Details");

        tvTitle=findViewById(R.id.tv_headline);
        tvDate=findViewById(R.id.tv_date);
        tvDetails=findViewById(R.id.tv_details);

        ivThumb=findViewById(R.id.iv_headline_thumb);
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

    private void loadData(){
        tvTitle.setText(article.getTitle());
        tvDetails.setText(article.getContent());
        tvDate.setText(article.getPublishedAt());
        Glide.with(mContext).load(article.getUrlToImage()).placeholder(R.color.grey_light).into(ivThumb);
        showDialogue("Do you want to open this in a browser?");



    }

    private void showDialogue(String title){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mActivity);
        if (title != null) {
            alertDialogBuilder.setTitle(title);
        }
        alertDialogBuilder.setCancelable(false);


            alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    openWebPage(article.getUrl());
                }
            });


            alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Intent intent = new Intent(mActivity, WebViewActivity.class);
                    intent.putExtra(AppConstants.URL, article.getUrl());
                    startActivity(intent);
                }
            });


        alertDialogBuilder.create().show();
    }

    public void openWebPage(String url) {
        try {
            Uri webpage = Uri.parse(url);
            Intent myIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(myIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No application can handle this request. Please install a web browser or check your URL.",  Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

}
