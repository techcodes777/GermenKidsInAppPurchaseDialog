package com.dingo.germanforkids.View;

import static com.dingo.germanforkids.purchase.Constant.switchState;
import static com.dingo.germanforkids.purchase.PurchaseClass.onPurchaseInitialization;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.adsnativetamplete.activity.NativeBaseActivity;
import com.adsnativetamplete.ads.InterstitialAds;
import com.adsnativetamplete.retrofit.AdsIdLoader;
import com.dingo.germanforkids.R;
import com.dingo.germanforkids.databinding.ActivityStartScreenBinding;
import com.dingo.germanforkids.purchase.Constant;
import com.dingo.germanforkids.purchase.Pref;
import com.dingo.germanforkids.utils.MyMediaPlayerBackground;
import com.dingo.germanforkids.utils.Utils;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;
import com.flaviofaria.kenburnsview.Transition;

public class StartScreenActivity extends NativeBaseActivity implements View.OnClickListener {

    ActivityStartScreenBinding binding;
    Dialog dialog;

    Switch soundOnOff;
    MyMediaPlayerBackground backsound;

    Dialog dialogCloseApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStartScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        onPurchaseInitialization(this);
        binding.imgPlay.setOnClickListener(this);
        binding.imgSetting.setOnClickListener(this);
        binding.imgRateUs.setOnClickListener(this);
        binding.imgExit.setOnClickListener(this);

        backsound = new MyMediaPlayerBackground(this);
        if (Utils.getPref(Constant.SOUND,true)){
            backsound.playSoundloop(R.raw.toy_piano);
        }else{
            backsound.StopMp();
        }
        dialogCloseApp = new Dialog(this);

        if (!Pref.getInstance().getString(Constant.DIALOG_P).equals("show")) {
            final Dialog dialog = new Dialog(StartScreenActivity.this);
            dialog.setContentView(R.layout.dialog_note);
            dialog.setCancelable(false);
            ((TextView) dialog.findViewById(R.id.tv_imp_note)).setMovementMethod(LinkMovementMethod.getInstance());
            dialog.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Pref.getInstance().setString(Constant.DIALOG_P, "show");
                    dialog.dismiss();
                }
            });

            dialog.findViewById(R.id.link1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setData(Uri.parse("https://learnkidsgame.ourportfolios.co/privacy-policy/"));
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
            });
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            dialog.show();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgPlay:
                InterstitialAds.showAds(this, new Intent(this, CategoriesActivity.class), false, true);
                break;
            case R.id.imgSetting:
                settingsDialog();
                break;
            case R.id.imgRateUs:
                rateDialog();
                break;
            case R.id.imgExit:
                closeTheApp();
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        backsound.StopMp();
        dialogCloseApp.dismiss();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Utils.getPref(Constant.SOUND,true)){
            backsound.playSoundloop(R.raw.toy_piano);
        }else{
            backsound.StopMp();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        backsound.StopMp();
        dialogCloseApp.dismiss();
    }

    @Override
    public void onBackPressed() {
        closeTheApp();
    }

    private void settingsDialog() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.setting_dialog);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));

        soundOnOff = dialog.findViewById(R.id.soundOnOff);
        if (Utils.getPref(Constant.SOUND, true)) {
            soundOnOff.setChecked(true);
            switchState = true;
        } else {
            switchState = false;
            soundOnOff.setChecked(false);
        }
        soundOnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchState) {
                    switchState = false;
                    soundOnOff.setChecked(false);
                    Utils.setPref(Constant.SOUND, false);
                    backsound.StopMp();
                } else {
                    switchState = true;
                    soundOnOff.setChecked(true);
                    Utils.setPref(Constant.SOUND, true);
                    backsound.playSoundloop(R.raw.toy_piano);

                }
            }
        });


        ImageView ivClose = dialog.findViewById(R.id.ivClose);
        ImageView imgPrivacy = dialog.findViewById(R.id.imgPrivacy);
        ImageView ivShare = dialog.findViewById(R.id.ivShare);

        imgPrivacy.setOnClickListener(v -> {
            startActivity(new Intent(this,PrivacyActivity.class));
            dialog.dismiss();
        });

        ivShare.setOnClickListener(v -> {
            shareApp();
        });

        CardView cvCard = dialog.findViewById(R.id.cvCard);
        RecyclerView rvCatAds = dialog.findViewById(R.id.rvCatAds);
        ProgressBar prog = dialog.findViewById(R.id.prog);
        AdsIdLoader.apiCategoryListCall(this, rvCatAds, prog, cvCard,"small", Pref.getInstance().getString(Constant.PURCHASE_DONE));

        ivClose.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    private void shareApp() {
        try {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("text/plain");
            intent.putExtra("android.intent.extra.SUBJECT", R.string.app_name);
            intent.putExtra("android.intent.extra.TEXT", "\nLet me recommend you this application\n\nhttps://play.google.com/store/apps/details?id=" + getPackageName() + "\n\n");
            startActivity(Intent.createChooser(intent, "choose one"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void rateDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.rate_dialog);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));


        ImageView ivClose = dialog.findViewById(R.id.ivClose);
        ImageView ivAboveDialog = dialog.findViewById(R.id.ivAboveDialog);

        ivAboveDialog.setOnClickListener(v -> {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName()));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
                dialog.dismiss();
            }
        });

        ivClose.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    private void closeTheApp() {
        dialogCloseApp.setContentView(R.layout.dialog_close_the_app);
        dialogCloseApp.setCancelable(false);
        dialogCloseApp.getWindow().setBackgroundDrawable(new ColorDrawable(0));

        TextView txtYes =  dialogCloseApp.findViewById(R.id.txtYes);
        TextView txtNo =  dialogCloseApp.findViewById(R.id.txtNo);

        txtYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });
        txtNo.setOnClickListener(view -> dialogCloseApp.cancel());

        ImageView ivClose = dialogCloseApp.findViewById(R.id.ivClose);

        ivClose.setOnClickListener(v -> dialogCloseApp.dismiss());

        dialogCloseApp.show();
    }
}