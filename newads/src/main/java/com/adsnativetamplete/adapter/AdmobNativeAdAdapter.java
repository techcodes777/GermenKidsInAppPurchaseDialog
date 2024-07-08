package com.adsnativetamplete.adapter;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static com.adsnativetamplete.AdsKeys.blinksAdsButton;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.adsnativetamplete.AdsKeys;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.ads.nativetemplates.R;
import com.google.android.gms.ads.nativead.NativeAd;

public class AdmobNativeAdAdapter extends RecyclerViewAdapterWrapper {
    public static final int TYPE_FB_NATIVE_ADS = 900;
    public static final int DEFAULT_AD_ITEM_INTERVAL = 10;
    private final Param mParam;
    private NativeAd nativeAd1;

    private AdmobNativeAdAdapter(Param param) {
        super(param.getAdapter());
        this.mParam = param;
        assertConfig();
        setSpanAds();
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    private void assertConfig() {
        if (mParam.gridLayoutManager != null) {
            //if user set span ads
            int nCol = mParam.gridLayoutManager.getSpanCount();
            if (mParam.adItemInterval % nCol != 0) {
                throw new IllegalArgumentException(String.format("The adItemInterval (%d) is not divisible by number of columns in GridLayoutManager (%d)", mParam.adItemInterval, nCol));
            }
        }
    }

    private int convertAdPosition2OrgPosition(int position) {
        return position - (position + 1) / (mParam.adItemInterval + 1);
    }

    @Override
    public int getItemCount() {
        int realCount = super.getItemCount();
        return realCount + realCount / mParam.adItemInterval;
    }

    @Override
    public int getItemViewType(int position) {
        if (isAdPosition(position)) {
            return TYPE_FB_NATIVE_ADS;
        }
        return super.getItemViewType(convertAdPosition2OrgPosition(position));
    }

    private boolean isAdPosition(int position) {
        return (position + 1) % (mParam.adItemInterval + 1) == 0;
    }

    public void onBindAdViewHolder(final RecyclerView.ViewHolder holder) {
        final AdViewHolder adHolder = (AdViewHolder) holder;
//        if (NativeAds.nativeAds != null) {
//            NativeTemplateStyle styles = new NativeTemplateStyle.Builder().build();
//            adHolder.ad_unit.setVisibility(View.VISIBLE);
////            adHolder.my_template.setStyles(styles);
//            adHolder.my_template.setNativeAd(NativeAds.nativeAds);
//        } else {
            adHolder.ad_unit.removeAllViews();
            if (mParam.current_mode.equals("Grid")) {
                LayoutInflater inflater = (LayoutInflater) mParam.context.getSystemService(LAYOUT_INFLATER_SERVICE);
                View childLayout = inflater.inflate(R.layout.custome_ad_unified_custome, null);
                TextView ad_headline = childLayout.findViewById(R.id.app_name);
                try {
                    ad_headline.setText(AdsKeys.app_name_ads);
                } catch (Exception e) {

                }
                TextView ad_body = childLayout.findViewById(R.id.ad_headline);
                try {
                    ad_body.setText(AdsKeys.description_one_ads);
                } catch (Exception e) {
                    e.printStackTrace();
                }


                try {
                    ((RequestBuilder) Glide.with(mParam.context)
                            .load(AdsKeys.image_small_ads)
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                            .into((ImageView) childLayout.findViewById(R.id.ad_app_icon));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                TextView ad_call_to_action = childLayout.findViewById(R.id.ad_call_to_action);
                ad_call_to_action.setText(AdsKeys.button_txt);
                if (blinksAdsButton.equals("true")) {
                    YoYo.with(Techniques.Flash).duration(2000).repeat(1000).playOn(childLayout.findViewById(R.id.ad_call_to_action));
                }
                ad_call_to_action.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(AdsKeys.play_store_url));
                            mParam.context.startActivity(intent);
                        } catch (Exception e) {
                        }
                    }
                });
                adHolder.ad_unit.removeAllViews();
                adHolder.ad_unit.addView(childLayout);
            } else if (mParam.current_mode.equals("GridSize")) {
                LayoutInflater inflater = (LayoutInflater) mParam.context.getSystemService(LAYOUT_INFLATER_SERVICE);
                View childLayout = inflater.inflate(R.layout.custome_ad_unified_custome, null);
                TextView ad_headline = childLayout.findViewById(R.id.app_name);
                try {
                    ad_headline.setText(AdsKeys.app_name_ads);
                } catch (Exception e) {

                }
                TextView ad_body = childLayout.findViewById(R.id.ad_headline);
                try {
                    ad_body.setText(AdsKeys.description_one_ads);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    ((RequestBuilder) Glide.with(mParam.context)
                            .load(AdsKeys.image_small_ads)
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                            .into((ImageView) childLayout.findViewById(R.id.ad_app_icon));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                TextView ad_call_to_action = childLayout.findViewById(R.id.ad_call_to_action);
                ad_call_to_action.setText(AdsKeys.button_txt);
                if (blinksAdsButton.equals("true")) {
                    YoYo.with(Techniques.Flash).duration(2000).repeat(1000).playOn(childLayout.findViewById(R.id.ad_call_to_action));
                }
                ad_call_to_action.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(AdsKeys.play_store_url));
                            mParam.context.startActivity(intent);
                        } catch (Exception e) {
                        }
                    }
                });
                adHolder.ad_unit.removeAllViews();
                adHolder.ad_unit.addView(childLayout);
            } else {
                LayoutInflater inflater = (LayoutInflater) mParam.context.getSystemService(LAYOUT_INFLATER_SERVICE);
                View childLayout = inflater.inflate(R.layout.custome_ads_nativ_admobfullmax, null);
                TextView ad_headline = childLayout.findViewById(R.id.ad_headline);
                try {
                    ad_headline.setText(AdsKeys.app_name_ads);
                } catch (Exception e) {

                }
                TextView ad_body = childLayout.findViewById(R.id.ad_body);
                try {
                    ad_body.setText(AdsKeys.description_one_ads);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    ((RequestBuilder) Glide.with(mParam.context)
                            .load(AdsKeys.image_small_ads)
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                            .into((ImageView) childLayout.findViewById(R.id.ad_app_icon));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    ((RequestBuilder) Glide.with(mParam.context)
                            .load(AdsKeys.image_big_ads)
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                            .into((ImageView) childLayout.findViewById(R.id.ad_media));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                TextView ad_call_to_action = childLayout.findViewById(R.id.ad_call_to_action);
                ad_call_to_action.setText(AdsKeys.button_txt);
                if (blinksAdsButton.equals("true")) {
                    YoYo.with(Techniques.Flash).duration(2000).repeat(1000).playOn(childLayout.findViewById(R.id.ad_call_to_action));
                }
                ad_call_to_action.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(AdsKeys.play_store_url));
                            mParam.context.startActivity(intent);
                        } catch (Exception e) {
                        }
                    }
                });
                adHolder.ad_unit.removeAllViews();
                adHolder.ad_unit.addView(childLayout);
            }
//        }
        adHolder.ad_unit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    mParam.context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(AdsKeys.play_store_url)));
                } catch (Exception e) {
                    Log.d("TAG", "onClick: ");
                }
            }
        });
        adHolder.ad_unit.setVisibility(View.VISIBLE);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_FB_NATIVE_ADS) {
            onBindAdViewHolder(holder);
        } else {
            super.onBindViewHolder(holder, convertAdPosition2OrgPosition(position));
        }
    }

    public RecyclerView.ViewHolder onCreateAdViewHolder(ViewGroup parent) {
        View v;
        if (mParam.current_mode.equals("Grid")) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_admob_native_ad_grid, parent, false);
        } else if (mParam.current_mode.equals("GridSize")) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_admob_native_ad_grid_size, parent, false);
        } else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_admob_native_ad, parent, false);
        }
        return new AdViewHolder(v);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FB_NATIVE_ADS) {
            return onCreateAdViewHolder(parent);
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    private void setSpanAds() {
        if (mParam.gridLayoutManager == null) {
            return;
        }
        final GridLayoutManager.SpanSizeLookup spl = mParam.gridLayoutManager.getSpanSizeLookup();
        mParam.gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (isAdPosition(position)) {
                    Log.d("hsfhghdfsafdgfas", "getSpanSize: ryergdhdgs");
                    return spl.getSpanSize(position);
                }
                Log.d("hsfhghdfsafdgfas", "getSpanSize: " + spl.getSpanSize(position));
                return 3;
            }
        });
    }

    private static class Param {
        public String Admob_ads_id;
        String current_mode = "Grid";
        int adItemInterval, dpSize;
        boolean forceReloadAdOnBind;
        Activity context;
        GridLayoutManager gridLayoutManager;
        private RecyclerView.Adapter adapter;

        public RecyclerView.Adapter getAdapter() {
            return adapter;
        }

        public void setAdapter(RecyclerView.Adapter adapter) {
            this.adapter = adapter;
        }
    }

    public static class Builder {
        private final Param mParam;

        private Builder(Param param) {
            mParam = param;
        }

        public static Builder with(Activity context, String placementId, String current_mode, RecyclerView.Adapter wrapped) {
            Param param = new Param();
            param.Admob_ads_id = placementId;
            param.current_mode = current_mode;
            param.context = context;
            param.setAdapter(wrapped);

            //default value
            param.adItemInterval = DEFAULT_AD_ITEM_INTERVAL;
            param.forceReloadAdOnBind = true;
            return new Builder(param);
        }

        public Builder adItemInterval(int interval) {
            mParam.adItemInterval = interval;
            return this;
        }

        public Builder adLayout(@LayoutRes int layoutContainerRes, @IdRes int itemContainerId) {
            return this;
        }

        public AdmobNativeAdAdapter build() {
            return new AdmobNativeAdAdapter(mParam);
        }

        public Builder enableSpanRow(GridLayoutManager layoutManager) {
            mParam.gridLayoutManager = layoutManager;
            return this;
        }

        public Builder forceReloadAdOnBind(boolean forced) {
            mParam.forceReloadAdOnBind = forced;
            return this;
        }
    }

    private static class AdViewHolder extends RecyclerView.ViewHolder {

        //LinearLayout nativeAdContainer;
        public TemplateView my_template;
        public LinearLayout ad_unit;

        AdViewHolder(View view) {
            super(view);
            my_template = view.findViewById(R.id.my_template);
            ad_unit = view.findViewById(R.id.ad_unit);
        }

        public Context getContext() {
            return my_template.getContext();
        }
    }
}