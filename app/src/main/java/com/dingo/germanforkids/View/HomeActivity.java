package com.dingo.germanforkids.View;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dingo.germanforkids.Adapter.MainAdapter;
import com.dingo.germanforkids.Model.ImageModel;
import com.dingo.germanforkids.R;
import com.dingo.germanforkids.databinding.ActivityHomeBinding;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityHomeBinding binding;

    private ArrayList<ImageModel> K() {
        ArrayList<ImageModel> arrayList = new ArrayList<>();
        TypedArray obtainType = getResources().obtainTypedArray(R.array.kids);
//
//        for (int i = 0; i < obtainType.length(); i++) {
//            arrayList.add(new ImageModel(obtainType.getResourceId(i, 1)));
//        }

        obtainType.recycle();
        return arrayList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.imgBack.setOnClickListener(this);

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setItemViewCacheSize(20);
        binding.recyclerView.setDrawingCacheEnabled(true);
        binding.recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        MainAdapter fVar = new MainAdapter(HomeActivity.this, K());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
        binding.recyclerView.setLayoutManager(gridLayoutManager);
        binding.recyclerView.setAdapter(fVar);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgBack:
                onBackPressed();
                break;
        }
    }


}