package com.adsnativetamplete.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adsnativetamplete.retrofit.models.CategoryItem;
import com.bumptech.glide.Glide;
import com.google.android.ads.nativetemplates.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private final String type;
    ArrayList<CategoryItem> langList;
    Context context;
    private int itemLayout;

    public CategoryAdapter(Context context2, ArrayList<CategoryItem> list, String type) {
        this.context = context2;
        this.langList = list;
        this.type = type;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (type.equals("small")) {
            itemLayout = R.layout.item_small_category;
        } else  if (type.equals("big")) {
            itemLayout = R.layout.item_big_category;
        }
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(itemLayout, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") int i) {
        CategoryItem rowItem = langList.get(i);
        viewHolder.tvAppName.setText(rowItem.getName());
        Glide.with(context).load(rowItem.getImageName()).into(viewHolder.ivLogo);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse(rowItem.getUrl()));
                    context.startActivity(viewIntent);
                } catch(Exception e) {
                    Toast.makeText(context,"Unable to Connect Try Again...", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
    }

    public int getItemCount() {
        return langList.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvAppName;
        ImageView ivLogo;

        public ViewHolder(View view) {
            super(view);
            ivLogo = view.findViewById(R.id.ivLogo);
            tvAppName = view.findViewById(R.id.tvAppName);
        }
    }
}
