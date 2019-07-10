package com.hcitest.testapi.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hcitest.testapi.R;
import com.hcitest.testapi.model.Item;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleHolder> {
    List<Item> articleList;
    Context ctx;
    LayoutInflater inflater;

    public ArticleAdapter(List<Item> articleList, Context ctx) {
        this.articleList = articleList;
        this.ctx = ctx;
        this.inflater = LayoutInflater.from(ctx);
    }

    @Override
    public ArticleHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ArticleHolder holder = new ArticleHolder(inflater.inflate(R.layout.defaultmenuitemlist, viewGroup, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ArticleHolder holder, int i) {
        holder.articleTitle.setText(articleList.get(i).getArticleTitle());
        Glide.with(ctx).load(articleList.get(i).getArticleImage()).into(holder.articleImage);
        holder.setArticleLink(articleList.get(i).getLink());

        final String url = holder.getArticleLink();
        holder.articleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                ctx.startActivity(browserIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class ArticleHolder extends RecyclerView.ViewHolder {
        public TextView articleTitle;
        public ImageView articleImage;
        public String articleLink;

        public ArticleHolder(View itemView) {
            super(itemView);
            articleImage = itemView.findViewById(R.id.imageView1);
            articleTitle = itemView.findViewById(R.id.txtView);
        }

        public String getArticleLink() {
            return articleLink;
        }

        public void setArticleLink(String articleLink) {
            this.articleLink = articleLink;
        }
    }
}
