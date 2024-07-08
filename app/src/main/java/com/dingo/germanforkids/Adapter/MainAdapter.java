package com.dingo.germanforkids.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dingo.germanforkids.Model.ImageModel;
import com.dingo.germanforkids.R;
import com.dingo.germanforkids.databinding.MainAdapterBinding;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    Context context;
    List<ImageModel> list;

    public MainAdapter(Context context, List<ImageModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(MainAdapterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageModel rowItem = list.get(position);
        Glide.with(context).load(rowItem.getImg()).placeholder(R.drawable.logo_splash).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.binding.gridImage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        MainAdapterBinding binding;

        public ViewHolder(MainAdapterBinding mainAdapterBinding) {
            super(mainAdapterBinding.getRoot());
            binding = mainAdapterBinding;
        }
    }

}
