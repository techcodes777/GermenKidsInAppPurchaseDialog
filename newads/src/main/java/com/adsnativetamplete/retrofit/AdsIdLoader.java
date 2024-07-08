package com.adsnativetamplete.retrofit;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adsnativetamplete.AdsKeys;
import com.adsnativetamplete.NativePreferenceUtil;
import com.adsnativetamplete.adapter.CategoryAdapter;
import com.adsnativetamplete.retrofit.models.AdsDetailsResponse;
import com.adsnativetamplete.retrofit.models.Category;
import com.adsnativetamplete.retrofit.models.CategoryItem;
import com.adsnativetamplete.retrofit.models.CategoryReq;
import com.adsnativetamplete.retrofit.models.GetProfileItem;
import com.adsnativetamplete.retrofit.models.appupdatesettings.AppUpdateResponse;
import com.adsnativetamplete.retrofit.models.appupdatesettings.GetProfile;
import com.google.android.ads.nativetemplates.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class AdsIdLoader {
    private static final String TAG = AdsIdLoader.class.getSimpleName();
    public static boolean loadingCall = true;
    static int scrollCount = 0;
    private static ArrayList<CategoryItem> categoryItemArrayList;

    public static void apiCall(Activity activity, String packageName, Handler handler) {
        Message message = new Message();

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<AdsDetailsResponse> call = apiInterface.getAdsDetails(packageName);
        call.enqueue(new retrofit2.Callback<AdsDetailsResponse>() {
            @Override
            public void onResponse(@NonNull Call<AdsDetailsResponse> call, @NonNull Response<AdsDetailsResponse> response) {
                if (response.code() == 200 && response.isSuccessful() && response.body() != null) {
                    if (response.body().getGetProfile() != null) {
                        new NativePreferenceUtil(activity).setAdsId(new Gson().toJson(response.body().getGetProfile()));
                        List<GetProfileItem> getProfileItems = response.body().getGetProfile();
                        settingAdsIds(activity, getProfileItems);
                    }
                }
                if (handler != null) {
                    handler.sendMessage(message);
                }
            }

            @Override
            public void onFailure(@NonNull Call<AdsDetailsResponse> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                if (handler != null) {
                    handler.sendMessage(message);
                }
            }
        });
    }

    public static void appUpdateCall(Activity activity, String packageName, Handler handler) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<AppUpdateResponse> call = apiInterface.getAppUpdate(packageName);
        call.enqueue(new retrofit2.Callback<AppUpdateResponse>() {
            @Override
            public void onResponse(@NonNull Call<AppUpdateResponse> call, @NonNull Response<AppUpdateResponse> response) {
                if (response.body() != null) {
                    if (response.body().isIsSuccess()) {
                        if (response.body().getGetProfile().isAppUpdatePopUp()) {
                            showDialog(activity, response.body().getGetProfile());
                        } else {
                            apiCall(activity, packageName, handler);
                        }
                    } else {
                        apiCall(activity, packageName, handler);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<AppUpdateResponse> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                apiCall(activity, packageName, handler);
            }
        });
    }

    public static void showDialog(Activity activity, com.adsnativetamplete.retrofit.models.appupdatesettings.GetProfile data) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_app_update);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        TextView text = dialog.findViewById(R.id.msg_desc);
        ImageView ivClose = dialog.findViewById(R.id.ivClose);
        Button dialogBtn_cancel = dialog.findViewById(R.id.btn_exit_no);
        if (!data.isCancel()) {
            ivClose.setVisibility(View.GONE);
        } else {
            ivClose.setVisibility(View.VISIBLE);
        }
        text.setText(data.getDescription());
        dialogBtn_cancel.setOnClickListener(v -> {
            dialog.dismiss();
            Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse(data.getAppLink()));
            activity.startActivity(viewIntent);
        });

        dialog.show();
    }

    public static void loadFromPref(Activity activity) {
        if (loadingCall) {
            loadingCall = false;
            GetProfile data = new Gson().fromJson(new NativePreferenceUtil(activity).getAdsId(), GetProfile.class);
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    AdsIdLoader.settingAdsIds(activity, (List<GetProfileItem>) data);
                    return null;
                }
            }.execute();
        }
    }

    public static void settingAdsIds(Activity activity, List<GetProfileItem> data) {
        try {

            //ADS Count
            AdsKeys.interstitialCount = data.get(0).getAdsCount().getInterstitial();
            if (AdsKeys.interstitialCount == null || AdsKeys.interstitialCount.equals("")) {
                AdsKeys.interstitialCount = "1";
            }

//            // Facebook
//            AdsKeys.interstitialIdFB = data.getAdxFive().getInterstitial();
//            AdsKeys.rewardedIdFb = data.getAdxFive().getRewarded();
//            AdsKeys.nativeIdFb = data.getAdxFive().getAdNative();
            AdsKeys.BannerAds1 = data.get(0).getGoogleAdmob().getBanner();
            AdsKeys.BannerAds2 = data.get(0).getAdxOne().getBanner();
            AdsKeys.BannerAds3 = data.get(0).getAdxTwo().getBanner();

            if (AdsKeys.BannerAds1 == null) {
                AdsKeys.BannerAds1 = "";
            }
            if (AdsKeys.BannerAds2 == null) {
                AdsKeys.BannerAds2 = "";
            }
            if (AdsKeys.BannerAds3 == null) {
                AdsKeys.BannerAds3 = "";
            }

            //Google Admob
            AdsKeys.interstitialId1 = data.get(0).getGoogleAdmob().getInterstitial();
            AdsKeys.rewardedId1 = data.get(0).getGoogleAdmob().getRewarded();
            AdsKeys.nativeId1 = data.get(0).getGoogleAdmob().getJsonMemberNative();
            AdsKeys.appOpenAdsIdOne = data.get(0).getGoogleAdmob().getAppOpen();

            if (AdsKeys.interstitialId1 == null) {
                AdsKeys.interstitialId1 = "";
            }
            if (AdsKeys.rewardedId1 == null) {
                AdsKeys.rewardedId1 = "";
            }
            if (AdsKeys.nativeId1 == null) {
                AdsKeys.nativeId1 = "";
            }

            if (AdsKeys.appOpenAdsIdOne == null) {
                AdsKeys.appOpenAdsIdOne = "";
            }

            SharedPreferences sharedpreferences = activity.getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("openid", AdsKeys.appOpenAdsIdOne);
            editor.commit();

            AdsKeys.interstitialIdAdmob = AdsKeys.interstitialId1;
            AdsKeys.rewardedIdAdmob = AdsKeys.rewardedId1;
            AdsKeys.nativeIdAdmob = AdsKeys.nativeId1;
            AdsKeys.appOpenAdsIdAdmob = AdsKeys.appOpenAdsIdOne;

            ///////////////////////////////////////////////

            //ADX ONE
            AdsKeys.interstitialId2 = data.get(0).getAdxOne().getInterstitial();
            AdsKeys.rewardedId2 = data.get(0).getAdxOne().getRewarded();
            AdsKeys.nativeId2 = data.get(0).getAdxOne().getJsonMemberNative();
            AdsKeys.appOpenAdsId2 = data.get(0).getAdxOne().getAppOpen();

            if (AdsKeys.interstitialId2 == null) {
                AdsKeys.interstitialId2 = "";
            }
            if (AdsKeys.rewardedId2 == null) {
                AdsKeys.rewardedId2 = "";
            }
            if (AdsKeys.nativeId2 == null) {
                AdsKeys.nativeId2 = "";
            }

            if (AdsKeys.appOpenAdsId2 == null) {
                AdsKeys.appOpenAdsId2 = "";
            }
            ///////////////////////////////////////////////

            //Adx Two
            AdsKeys.interstitialId3 = data.get(0).getAdxTwo().getInterstitial();
            AdsKeys.rewardedId3 = data.get(0).getAdxTwo().getRewarded();
            AdsKeys.nativeId3 = data.get(0).getAdxTwo().getJsonMemberNative();
            AdsKeys.appOpenAdsId3 = data.get(0).getAdxTwo().getAppOpen();
            if (AdsKeys.interstitialId3 == null) {
                AdsKeys.interstitialId3 = "";
            }
            if (AdsKeys.rewardedId3 == null) {
                AdsKeys.rewardedId3 = "";
            }
            if (AdsKeys.nativeId3 == null) {
                AdsKeys.nativeId3 = "";
            }

            if (AdsKeys.appOpenAdsId3 == null) {
                AdsKeys.appOpenAdsId3 = "";
            }
            //////////////////////////////////////////////


            //ADX THREE
            String inter4 = data.get(0).getAdxThree().getInterstitial();
            if (inter4.equals("")) {
                AdsKeys.interstitialId4 = 1676518013664L;
            } else {
                AdsKeys.interstitialId4 = Long.valueOf(inter4);
            }
            AdsKeys.rewardedId4 = data.get(0).getAdxThree().getRewarded();
            String native4 = data.get(0).getAdxThree().getJsonMemberNative();
            if (native4.equals("")) {
                AdsKeys.nativeId4 = 1678637495670L;
            } else {
                AdsKeys.nativeId4 = Long.valueOf(native4);
            }

            AdsKeys.appOpenAdsId4 = data.get(0).getAdxThree().getAppOpen();
            if (AdsKeys.rewardedId4 == null) {
                AdsKeys.rewardedId4 = "";
            }

            if (AdsKeys.appOpenAdsId4 == null) {
                AdsKeys.appOpenAdsId4 = "";
            }
            /////////////////////////////////////////////////


            //ADX FOUR
            AdsKeys.interstitialId5 = data.get(0).getAdxFour().getInterstitial();
            AdsKeys.rewardedId5 = data.get(0).getAdxFour().getRewarded();
            AdsKeys.nativeId5 = data.get(0).getAdxFour().getJsonMemberNative();
            AdsKeys.appOpenAdsId5 = data.get(0).getAdxFour().getAppOpen();
            if (AdsKeys.interstitialId5 == null) {
                AdsKeys.interstitialId5 = "";
            }
            if (AdsKeys.rewardedId5 == null) {
                AdsKeys.rewardedId5 = "";
            }
            if (AdsKeys.nativeId5 == null) {
                AdsKeys.nativeId5 = "";
            }

            if (AdsKeys.appOpenAdsId5 == null) {
                AdsKeys.appOpenAdsId5 = "";
            }
            /////////////////////////////////////////////////


            //ADX Five
            AdsKeys.interstitialId6 = data.get(0).getAdxFive().getInterstitial();
            AdsKeys.rewardedId6 = data.get(0).getAdxFive().getRewarded();
            AdsKeys.nativeId6 = data.get(0).getAdxFive().getJsonMemberNative();
            AdsKeys.appOpenAdsId6 = data.get(0).getAdxFive().getAppOpen();
            if (AdsKeys.interstitialId6 == null) {
                AdsKeys.interstitialId6 = "";
            }
            if (AdsKeys.rewardedId6 == null) {
                AdsKeys.rewardedId6 = "";
            }
            if (AdsKeys.nativeId6 == null) {
                AdsKeys.nativeId6 = "";
            }

            if (AdsKeys.appOpenAdsId6 == null) {
                AdsKeys.appOpenAdsId6 = "";
            }
            /////////////////////////////////////////////////

            //Custom ADS
            AdsKeys.image_small_ads = data.get(0).getCustomAds().getCustomImageSmall();
            AdsKeys.image_big_ads = data.get(0).getCustomAds().getCustomImageBig();
            AdsKeys.app_name_ads = data.get(0).getCustomAds().getAppNameAds();
            AdsKeys.description_one_ads = data.get(0).getCustomAds().getDescriptionOneAds();
            AdsKeys.description_two_ads = data.get(0).getCustomAds().getDescriptionTwoAds();
            AdsKeys.button_txt = data.get(0).getCustomAds().getButtonTxt();
            AdsKeys.play_store_url = data.get(0).getCustomAds().getPlayStoreUrl();

            if (AdsKeys.image_small_ads == null) {
                AdsKeys.image_small_ads = "";
            }
            if (AdsKeys.image_big_ads == null) {
                AdsKeys.image_big_ads = "";
            }
            if (AdsKeys.app_name_ads == null) {
                AdsKeys.app_name_ads = "";
            }
            if (AdsKeys.description_one_ads == null) {
                AdsKeys.description_one_ads = "";
            }
            if (AdsKeys.description_two_ads == null) {
                AdsKeys.description_two_ads = "";
            }
            if (AdsKeys.button_txt == null) {
                AdsKeys.button_txt = "";
            }
            if (AdsKeys.play_store_url == null) {
                AdsKeys.play_store_url = "";
            }

            // firstScreenSetting
            AdsKeys.firstScreenSetting = data.get(0).getFirstScreenSetting().getIntersitialAppOpen();

            // onSignalIDS
            for (int i = 0; i < data.get(0).getOtherInputFields().size(); i++) {
                switch (data.get(0).getOtherInputFields().get(i).getFieldName()) {
                    case "OnesignalAppId":
                        AdsKeys.onesignalIds = data.get(0).getOtherInputFields().get(i).getValue();
                        break;
                    case "BlinksAdsButton":
                        boolean blinkButton = Boolean.parseBoolean(data.get(0).getOtherInputFields().get(i).getValue());
                        AdsKeys.blinksAdsButton = String.valueOf(blinkButton);
                        break;
                    case "IsCustomAdsDisplay":
                        boolean customeIs = Boolean.parseBoolean(data.get(0).getOtherInputFields().get(i).getValue());
                        AdsKeys.Custom = String.valueOf(customeIs);
                        break;
                    case "WithFBAdsDisplay":
                        boolean btmOnoff = Boolean.parseBoolean(data.get(0).getOtherInputFields().get(i).getValue());
                        AdsKeys.WithFB = String.valueOf(btmOnoff);
                        break;
                    case "WithAppLovingAdsDisplay":
                        boolean WithAppLovingAdsDisplay = Boolean.parseBoolean(data.get(0).getOtherInputFields().get(i).getValue());
                        AdsKeys.WithAppLoving = String.valueOf(WithAppLovingAdsDisplay);
                        break;
                    case "AllAdsLoadingDisplay":
                        boolean AllAdsLoadingDisplay = Boolean.parseBoolean(data.get(0).getOtherInputFields().get(i).getValue());
                        AdsKeys.AllAdsLoading = String.valueOf(AllAdsLoadingDisplay);
                        break;
                    case "InMobiAdsDisplay":
                        boolean InMobiAdsDisplay = Boolean.parseBoolean(data.get(0).getOtherInputFields().get(i).getValue());
                        AdsKeys.InMobi = String.valueOf(InMobiAdsDisplay);
                        break;
                    case "ShowRateAdsDisplay":
                        boolean ShowRateAdsDisplay = Boolean.parseBoolean(data.get(0).getOtherInputFields().get(i).getValue());
                        AdsKeys.ShowRateAds = String.valueOf(ShowRateAdsDisplay);
                        break;
                    case "MultipleScreenDisplay":
                        boolean MultipleScreenDisplay = Boolean.parseBoolean(data.get(0).getOtherInputFields().get(i).getValue());
                        AdsKeys.multiScreenDisplay = String.valueOf(MultipleScreenDisplay);
                        break;
                    case "dropdown":
                        String str = data.get(0).getOtherInputFields().get(i).getValue();
                        if (str.isEmpty()) {
                            Log.e(TAG, "Data Is Empty: " + str);
                        } else {
                            AdsKeys.dropdown = str;
                        }
                        break;

                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void apiCategoryListCall(Activity activity, RecyclerView rvAdsList, ProgressBar progressBar, CardView cvCard, String type, String done) {
        progressBar.setVisibility(View.VISIBLE);
        CategoryReq categoryReq = new CategoryReq();
        categoryReq.setCategoryName(AdsKeys.dropdown);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Category> call = apiInterface.getCategoryApp(categoryReq);
        call.enqueue(new retrofit2.Callback<Category>() {
            @Override
            public void onResponse(@NonNull Call<Category> call, @NonNull Response<Category> response) {
                if (response.code() == 200 && response.isSuccessful() && response.body() != null) {

                    if (done.equals("done")) {
                        cvCard.setVisibility(View.GONE);
                    }else {
                        categoryItemArrayList = response.body().getData();

                        progressBar.setVisibility(View.GONE);
                        rvAdsList.setVisibility(View.VISIBLE);
                        cvCard.setVisibility(View.VISIBLE);
                        CategoryAdapter categoryAdapter = new CategoryAdapter(activity, categoryItemArrayList, type);
                        autoScrollAnother(categoryAdapter, rvAdsList);
                        rvAdsList.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false));
                        rvAdsList.setHasFixedSize(true);
                        rvAdsList.setAdapter(categoryAdapter);
                    }
                } else {
                    progressBar.setVisibility(View.GONE);
                    rvAdsList.setVisibility(View.GONE);
                    cvCard.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Category> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                progressBar.setVisibility(View.GONE);
                rvAdsList.setVisibility(View.GONE);
                cvCard.setVisibility(View.GONE);
            }
        });
    }

    public static void autoScrollAnother(CategoryAdapter categoryAdapter, RecyclerView rvAdsList) {
        scrollCount = 0;
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                rvAdsList.smoothScrollToPosition((scrollCount++));
                if (scrollCount == categoryAdapter.getItemCount() - 1) {
                    categoryItemArrayList.addAll(categoryItemArrayList);
                    categoryAdapter.notifyDataSetChanged();
                }
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(runnable, 1000);
    }
}