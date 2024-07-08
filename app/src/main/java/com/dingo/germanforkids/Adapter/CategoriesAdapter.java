package com.dingo.germanforkids.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dingo.germanforkids.Model.CategoriesModel;
import com.dingo.germanforkids.R;
import com.dingo.germanforkids.View.CategoriesActivity;

import java.util.List;


public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.myViewHolder> {
    private List<CategoriesModel> categoriesList;
    private Context context;

    public CategoriesAdapter(Context context, List<CategoriesModel> list) {
        this.context = context;
        this.categoriesList = list;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        this.context = context;
        return new myViewHolder(LayoutInflater.from(context).inflate(R.layout.row_categories, viewGroup, false));
    }

    @Override 
    public void onBindViewHolder(@NonNull myViewHolder myviewholder, @SuppressLint("RecyclerView") final int i) {
        if (categoriesList != null) {
            myviewholder.img.setImageResource(categoriesList.get(i).getImage());
            myviewholder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override 
                public void onClick(View view) {
                    ((CategoriesActivity) context).intentToBridgeActivity(((CategoriesModel) categoriesList.get(i)).getName(),i);
                }
            });
        }
    }

    @Override 
    public int getItemCount() {
        List<CategoriesModel> list = categoriesList;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        LinearLayout linearLayout;

        public myViewHolder(View view) {
            super(view);
            img = (ImageView) view.findViewById(R.id.img);
            linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
        }
    }
}
