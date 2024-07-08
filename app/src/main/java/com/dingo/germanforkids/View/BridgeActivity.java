package com.dingo.germanforkids.View;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adsnativetamplete.ads.InterstitialAds;
import com.adsnativetamplete.retrofit.AdsIdLoader;
import com.dingo.germanforkids.Adapter.BridgeAdapter;
import com.dingo.germanforkids.Model.CategoriesModel;
import com.dingo.germanforkids.R;
import com.dingo.germanforkids.databinding.ActivityBridgeBinding;
import com.dingo.germanforkids.purchase.Constant;
import com.dingo.germanforkids.purchase.Pref;

import java.util.ArrayList;
import java.util.List;


public class BridgeActivity extends AppCompatActivity implements View.OnClickListener {
    private List<CategoriesModel> bridgeList;
    private String name;
    private boolean openedCard = false;
    ActivityBridgeBinding binding;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(67108864);
            window.addFlags(Integer.MIN_VALUE);
        }
        binding = ActivityBridgeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initComponents();
        binding.imgBack.setOnClickListener(this);
        binding.txtTittle.setText(name);

        CardView cvCard = findViewById(R.id.cvCard);
        RecyclerView rvCatAds = findViewById(R.id.rvCatAds);
        ProgressBar prog = findViewById(R.id.prog);
        AdsIdLoader.apiCategoryListCall(BridgeActivity.this, rvCatAds, prog, cvCard,"small", Pref.getInstance().getString(Constant.PURCHASE_DONE));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void initComponents() {
        bridgeList = new ArrayList();

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("name")) {
            this.name = intent.getExtras().getString("name");
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        setListData();
        recyclerView.setAdapter(new BridgeAdapter(this, bridgeList));
    }

    private void setListData() {
        String[] strArr;
        int[] iArr;
        strArr = new String[]{"Lernen", "Schreiben", "Bilder finden"};
        iArr = new int[]{R.drawable.learn_btn, R.drawable.write_btn, R.drawable.find_picture_btn};
        for (int i = 0; i < strArr.length; i++) {
            bridgeList.add(new CategoriesModel(iArr[i], strArr[i]));
        }
    }

    public void intentToShowActivity(String str) {
        openedCard = true;
        new Intent();
        if (str.equalsIgnoreCase("Lernen")) {
            InterstitialAds.showAds(this, new Intent(this, ShowActivity.class).putExtra("name", name), false, true);
        } else if (str.equalsIgnoreCase("Schreiben")) {
            if (name.equalsIgnoreCase("Alphabet")) {
                InterstitialAds.showAds(this, new Intent(this, MainDraw.class).putExtra("name", name), false, true);
            } else if (name.equalsIgnoreCase("Zahlen")) {
                InterstitialAds.showAds(this, new Intent(this, WritingDialogActivity.class).putExtra("name", name), false, true);
            } else {
                InterstitialAds.showAds(this, new Intent(this, Writing.class).putExtra("name", name), false, true);
            }
        } else if (str.equalsIgnoreCase("Wörter finden")) {
            InterstitialAds.showAds(this, new Intent(this, FindTheWord.class).putExtra("name", name), false, true);
        } else if (str.equalsIgnoreCase("Bilder finden")) {
            InterstitialAds.showAds(this, new Intent(this, FindThePic.class).putExtra("name", name), false, true);
        } else if (str.equalsIgnoreCase("Der Die Das")) {
            InterstitialAds.showAds(this, new Intent(this, DerDieDas.class).putExtra("name", name), false, true);
        }
    }


}
