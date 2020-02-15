package com.mufadmonwar.newsviewsv2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mufadmonwar.newsviewsv2.R;
import com.mufadmonwar.newsviewsv2.listeners.ItemClickListener;
import com.mufadmonwar.newsviewsv2.model.headlines.Article;
import com.mufadmonwar.newsviewsv2.model.news_source.Source;

import java.util.ArrayList;

public class TopHeadlineAdapter extends RecyclerView.Adapter<TopHeadlineAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Article> arrayList;

    private ItemClickListener itemClickListener;

    public TopHeadlineAdapter(Context context, ArrayList<Article> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_headlines, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitle.setText(arrayList.get(position).getTitle());
        Glide.with(context).load(arrayList.get(position).getUrlToImage()).into(holder.ivNewsThumb);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvTitle;
        ImageView ivNewsThumb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_headline_title);
            ivNewsThumb=itemView.findViewById(R.id.iv_headline_thumb);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

}
