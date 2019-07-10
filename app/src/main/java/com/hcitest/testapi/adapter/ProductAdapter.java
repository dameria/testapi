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

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {
    List<Item> productList;
    Context ctx;
    LayoutInflater mInflater;

    public ProductAdapter(List<Item> productList, Context ctx) {
        this.productList = productList;
        this.ctx = ctx;
        this.mInflater = LayoutInflater.from(ctx);
    }

    @Override
    public ProductHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ProductHolder holder = new ProductHolder(mInflater.inflate(R.layout.defaultmenuitemgrid, viewGroup, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ProductHolder holder, int i) {
        holder.menuTitle.setText(productList.get(i).getProductName());
        Glide.with(ctx).load(productList.get(i).getProductImage()).into(holder.menuImage);
        holder.setMenuLink(productList.get(i).getLink());

        final String url = holder.getMenuLink();
        holder.menuImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open url in browser
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                ctx.startActivity(browserIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder {
        public TextView menuTitle;
        public ImageView menuImage;
        public String menuLink;

        public ProductHolder(View v) {
            super(v);
            menuImage = v.findViewById(R.id.imageView1);
            menuTitle = v.findViewById(R.id.txtView);
        }

        public String getMenuLink() {
            return menuLink;
        }

        public void setMenuLink(String menuLink) {
            this.menuLink = menuLink;
        }
    }
}
