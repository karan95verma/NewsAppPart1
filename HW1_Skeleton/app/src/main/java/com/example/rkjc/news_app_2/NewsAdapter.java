package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsItemViewHolder> {

    Context mContext;
    ArrayList<NewsItem> mNews;

    public NewsAdapter(Context context, ArrayList<NewsItem> news){
        this.mContext = context;
        this.mNews = news;
    }

    @Override
    public NewsAdapter.NewsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.news_item, parent, shouldAttachToParentImmediately);
        NewsAdapter.NewsItemViewHolder viewHolder = new NewsAdapter.NewsItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsAdapter.NewsItemViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }

    public class NewsItemViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        String url;
        TextView publishedAt;

        public NewsItemViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            publishedAt = (TextView) itemView.findViewById(R.id.publishedAt);
        }

        void bind(final int listIndex) {
            title.setText("Title : " + mNews.get(listIndex).getTitle());
            description.setText("Description : " + mNews.get(listIndex).getDescription());
            publishedAt.setText("Date : " + mNews.get(listIndex).getPublishedAt());
            url = mNews.get(listIndex).getUrl();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String urlString = mNews.get(getAdapterPosition()).getUrl();
                    // opens in default browser
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
                    mContext.startActivity(browserIntent);
                }
            });
        }
    }

}