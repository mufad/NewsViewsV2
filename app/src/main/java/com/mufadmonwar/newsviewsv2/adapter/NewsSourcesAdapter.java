package com.mufadmonwar.newsviewsv2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mufadmonwar.newsviewsv2.R;
import com.mufadmonwar.newsviewsv2.listeners.ItemClickListener;
import com.mufadmonwar.newsviewsv2.model.news_source.Source;

import java.util.ArrayList;

public class NewsSourcesAdapter extends RecyclerView.Adapter<NewsSourcesAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Source> arrayList;

    private ItemClickListener itemClickListener;

    public NewsSourcesAdapter(Context context, ArrayList<Source> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_sources, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitle.setText(arrayList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_news_source_title);
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
