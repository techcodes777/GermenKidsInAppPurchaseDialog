package com.dingo.germanforkids.View;

import static com.dingo.germanforkids.purchase.PurchaseClass.onClickRemoveAds;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adsnativetamplete.ads.InterstitialAds;
import com.adsnativetamplete.retrofit.AdsIdLoader;
import com.dingo.germanforkids.Adapter.CategoriesAdapter;
import com.dingo.germanforkids.Model.CategoriesModel;
import com.dingo.germanforkids.R;
import com.dingo.germanforkids.databinding.ActivityCategoriesBinding;
import com.dingo.germanforkids.purchase.Constant;
import com.dingo.germanforkids.purchase.Pref;

import java.util.ArrayList;
import java.util.List;


public class CategoriesActivity extends AppCompatActivity implements View.OnClickListener {
    List<CategoriesModel> categoriesList;

    private boolean moreSub;
    private boolean noAds;

    ActivityCategoriesBinding binding;
    int currIndex = 0;
    int startIndex = 0;

    @Override

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        binding = ActivityCategoriesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        readSharedPrefForAdsAndMoreSub();
        initComponents();
        binding.imgBack.setOnClickListener(this);
        binding.imgAd.setOnClickListener(this);

        CardView cvCard = findViewById(R.id.cvCard);
        RecyclerView rvCatAds = findViewById(R.id.rvCatAds);
        ProgressBar prog = findViewById(R.id.prog);
        AdsIdLoader.apiCategoryListCall(CategoriesActivity.this, rvCatAds, prog, cvCard, "small", Pref.getInstance().getString(Constant.PURCHASE_DONE));

        if (!Pref.getInstance().getString(Constant.PURCHASE_DONE).equals("done")) {
            if (Pref.getInstance().getString(Constant.FIRST_OPEN).equals("")) {
                if (startIndex == 0) {
                    purchaseDialog(R.layout.dialog_purchase);
                    Pref.getInstance().setString(Constant.FIRST_OPEN, "first_op");
                    ++startIndex;
                } else if (startIndex == 1) {
                    purchaseDialog(R.layout.dialog_purchase2);
                    Pref.getInstance().setString(Constant.FIRST_OPEN, "first_op");
                    ++startIndex;
                } else if (startIndex == 2) {
                    purchaseDialog(R.layout.dialog_purchase3);
                    Pref.getInstance().setString(Constant.FIRST_OPEN, "first_op");
                    startIndex = 0;
                }
            }
        } else {
            binding.imgAd.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Pref.getInstance().getString(Constant.PURCHASE_DONE).equals("done")){
            binding.imgAd.setVisibility(View.GONE);
        }else {
            binding.imgAd.setVisibility(View.VISIBLE);
        }
    }

    private void readSharedPrefForAdsAndMoreSub() {
        SharedPreferences sharedPreferences = getSharedPreferences("store", 0);
        this.noAds = sharedPreferences.getBoolean("removeAds", false);
        this.moreSub = sharedPreferences.getBoolean("openNewSubjects", false);
    }

    private void initComponents() {
        this.categoriesList = new ArrayList();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        setListData();
        recyclerView.setAdapter(new CategoriesAdapter(this, this.categoriesList));
    }

    private void setListData() {
        String[] strArr;
        String[] stringArray;
        int[] iArr;
        int[] iArrLock;
        iArr = new int[]{R.drawable.num1, R.drawable.num2, R.drawable.num3, R.drawable.num4, R.drawable.num5, R.drawable.num6, R.drawable.num7, R.drawable.num8, R.drawable.num9, R.drawable.num10, R.drawable.num11, R.drawable.num12, R.drawable.num13, R.drawable.num14, R.drawable.num15, R.drawable.num16, R.drawable.num17, R.drawable.num18, R.drawable.num19, R.drawable.num20};
        iArrLock = new int[]{R.drawable.num1, R.drawable.num2_lock, R.drawable.num3_lock, R.drawable.num4, R.drawable.num5, R.drawable.num6_lock, R.drawable.num7_lock, R.drawable.num8, R.drawable.num9, R.drawable.num10_lock, R.drawable.num11_lock, R.drawable.num12, R.drawable.num13, R.drawable.num14_lock, R.drawable.num15_lock, R.drawable.num16, R.drawable.num17, R.drawable.num18_lock, R.drawable.num19_lock, R.drawable.num20};

        strArr = new String[]{"Alphabet", "Zahlen", "Farben", "Formen", "Familie", "Körperteile", "Wochentage", "Monate", "Obst", "Gemüse", "Tiere", "Vögel", "Essen", "Kleidung", "Küche", "Badezimmer", "Wohnzimmer", "Schule", "Sport", "Berufe"};
        stringArray = getResources().getStringArray(R.array.translateArray);

        if (!Pref.getInstance().getString(Constant.PURCHASE_DONE).equals("done")) {
            for (int i = 0; i < iArr.length; i++) {
                this.categoriesList.add(new CategoriesModel(iArrLock[i], strArr[i], stringArray[i]));
            }
        } else {
            for (int i = 0; i < iArr.length; i++) {
                this.categoriesList.add(new CategoriesModel(iArr[i], strArr[i], stringArray[i]));
            }
        }

    }

    public void intentToBridgeActivity(String str, int i) {

        switch (i) {
            case 1:
            case 2:
            case 5:
            case 6:
            case 9:
            case 10:
            case 13:
            case 14:
            case 17:
            case 18:
                if (!Pref.getInstance().getString(Constant.PURCHASE_DONE).equals("done")) {
                    removeAdsDialog(new Intent(this, BridgeActivity.class).putExtra("name", str));
                } else {
                    InterstitialAds.showAds(this, new Intent(this, BridgeActivity.class).putExtra("name", str), false, true);
                }
                break;
            case 0:
            case 3:
            case 4:
            case 7:
            case 8:
            case 11:
            case 12:
            case 15:
            case 16:
            case 19:
                InterstitialAds.showAds(this, new Intent(this, BridgeActivity.class).putExtra("name", str), false, true);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                onBackPressed();
                break;
            case R.id.imgAd:
                if (!Pref.getInstance().getString(Constant.PURCHASE_DONE).equals("done")) {
                    if (currIndex == 0) {
                        purchaseDialog(R.layout.dialog_purchase);
                        ++currIndex;
                    } else if (currIndex == 1) {
                        purchaseDialog(R.layout.dialog_purchase2);
                        ++currIndex;
                    } else if (currIndex == 2) {
                        purchaseDialog(R.layout.dialog_purchase3);
                        currIndex = 0;
                    }
                } else {
                    binding.imgAd.setVisibility(View.GONE);
                }
                break;
        }
    }

    private void purchaseDialog(int layout) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(layout);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));

        TextView ivPrice = dialog.findViewById(R.id.ivPrice);
        ImageView ivClose = dialog.findViewById(R.id.ivClose);
        RadioButton radioButton = dialog.findViewById(R.id.radioButton);
        ImageView ivOk = dialog.findViewById(R.id.ivOk);
        TextView title = dialog.findViewById(R.id.tvTitle);
        LinearLayout main = dialog.findViewById(R.id.llMain);

        title.setText("Purchase and Remove Ads");
        ivPrice.setText(Pref.getInstance().getString(Constant.PRICE));

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButton.setChecked(!radioButton.isChecked());
            }
        });

        ivOk.setOnClickListener(v -> {
            if (radioButton.isChecked()) {
                dialog.dismiss();
                onClickRemoveAds();
            } else {
                Toast.makeText(this, "First select the remove ad plan.", Toast.LENGTH_SHORT).show();
            }
        });

        ivClose.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    private void removeAdsDialog(Intent intent) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_ads_remove);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));

        ImageView ivClose = dialog.findViewById(R.id.ivClose);
        TextView ivPrice = dialog.findViewById(R.id.ivPrice);
        TextView title = dialog.findViewById(R.id.tvTitle);
        LinearLayout llWatchVideo = dialog.findViewById(R.id.llWatchVideo);
        LinearLayout main = dialog.findViewById(R.id.llMain);

        title.setText("Remove Ads");
        ivPrice.setText(Pref.getInstance().getString(Constant.PRICE));

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                onClickRemoveAds();
            }
        });

        llWatchVideo.setOnClickListener(v -> {
            dialog.dismiss();
            InterstitialAds.showAds(this, intent, false, false);
        });

        ivClose.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }



}
