package com.adsnativetamplete.adapter;

import static com.adsnativetamplete.AdsKeys.blinksAdsButton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.adsnativetamplete.AdsKeys;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.ads.nativetemplates.R;
import com.google.android.gms.ads.nativead.MediaView;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;

/**
 * Base class for a template view. *
 */
public class TemplateView extends FrameLayout {

    private static final String MEDIUM_TEMPLATE = "medium_template";
    private static final String SMALL_TEMPLATE = "small_template";
    private Context context;
    private int templateType;
    private NativeTemplateStyle styles;
    private NativeAd nativeAd;
    private NativeAdView nativeAdView;
    private TextView primaryView;
    private TextView secondaryView;
    private TextView ad_attribution;
    private RatingBar ratingBar;
    private TextView tertiaryView;
    private ImageView iconView;
    private AppCompatButton callToActionView;
    private ConstraintLayout background;
    private MediaView media_view;

    public TemplateView(Context context) {
        super(context);
        this.context = context;
    }

    public TemplateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public TemplateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    public TemplateView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context, attrs);
    }

    public void setStyles(NativeTemplateStyle styles) {
        this.styles = styles;
        this.applyStyles();
    }

    public NativeAdView getNativeAdView() {
        return nativeAdView;
    }

    private void applyStyles() {

        Drawable mainBackground = styles.getMainBackgroundColor();
        if (mainBackground != null) {
            background.setBackground(mainBackground);
            if (primaryView != null) {
                primaryView.setBackground(mainBackground);
            }
            if (secondaryView != null) {
                secondaryView.setBackground(mainBackground);
            }
            if (tertiaryView != null) {
                tertiaryView.setBackground(mainBackground);
            }
        }

        Typeface primary = styles.getPrimaryTextTypeface();
        if (primary != null && primaryView != null) {
            primaryView.setTypeface(primary);
        }

        Typeface secondary = styles.getSecondaryTextTypeface();
        if (secondary != null && secondaryView != null) {
            secondaryView.setTypeface(secondary);
        }

        Typeface tertiary = styles.getTertiaryTextTypeface();
        if (tertiary != null && tertiaryView != null) {
            tertiaryView.setTypeface(tertiary);
        }

        Typeface ctaTypeface = styles.getCallToActionTextTypeface();
        if (ctaTypeface != null && callToActionView != null) {
            callToActionView.setTypeface(ctaTypeface);

        }

        int primaryTypefaceColor = styles.getPrimaryTextTypefaceColor();
        if (primaryTypefaceColor > 0 && primaryView != null) {
            primaryView.setTextColor(primaryTypefaceColor);
        }

        int secondaryTypefaceColor = styles.getSecondaryTextTypefaceColor();
        if (secondaryTypefaceColor > 0 && secondaryView != null) {
            secondaryView.setTextColor(secondaryTypefaceColor);
        }

        int tertiaryTypefaceColor = styles.getTertiaryTextTypefaceColor();
        if (tertiaryTypefaceColor > 0 && tertiaryView != null) {
            tertiaryView.setTextColor(tertiaryTypefaceColor);
        }

        int ctaTypefaceColor = styles.getCallToActionTypefaceColor();
        if (ctaTypefaceColor > 0 && callToActionView != null) {
            callToActionView.setTextColor(ctaTypefaceColor);
        }

        float ctaTextSize = styles.getCallToActionTextSize();
        if (ctaTextSize > 0 && callToActionView != null) {
            callToActionView.setTextSize(ctaTextSize);
        }

        float primaryTextSize = styles.getPrimaryTextSize();
        if (primaryTextSize > 0 && primaryView != null) {
            primaryView.setTextSize(primaryTextSize);
        }

        float secondaryTextSize = styles.getSecondaryTextSize();
        if (secondaryTextSize > 0 && secondaryView != null) {
            secondaryView.setTextSize(secondaryTextSize);
        }

        float tertiaryTextSize = styles.getTertiaryTextSize();
        if (tertiaryTextSize > 0 && tertiaryView != null) {
            tertiaryView.setTextSize(tertiaryTextSize);
        }

        Drawable ctaBackground = styles.getCallToActionBackgroundColor();
        if (ctaBackground != null && callToActionView != null) {
            callToActionView.setBackground(ctaBackground);
        }

        Drawable primaryBackground = styles.getPrimaryTextBackgroundColor();
        if (primaryBackground != null && primaryView != null) {
            primaryView.setBackground(primaryBackground);
        }

        Drawable secondaryBackground = styles.getSecondaryTextBackgroundColor();
        if (secondaryBackground != null && secondaryView != null) {
            secondaryView.setBackground(secondaryBackground);
        }

        Drawable tertiaryBackground = styles.getTertiaryTextBackgroundColor();
        if (tertiaryBackground != null && tertiaryView != null) {
            tertiaryView.setBackground(tertiaryBackground);
        }

        invalidate();
        requestLayout();
    }

    private boolean adHasOnlyStore(NativeAd nativeAd) {
        String store = nativeAd.getStore();
        String advertiser = nativeAd.getAdvertiser();
        return !TextUtils.isEmpty(store) && TextUtils.isEmpty(advertiser);
    }

    public void setNativeAd(NativeAd nativeAd) {
        this.nativeAd = nativeAd;

        String store = nativeAd.getStore();
        String advertiser = nativeAd.getAdvertiser();
        String headline = nativeAd.getHeadline();
        String body = nativeAd.getBody();
        String cta = nativeAd.getCallToAction();
        Double starRating = nativeAd.getStarRating();
        NativeAd.Image icon = nativeAd.getIcon();

        String secondaryText;

        nativeAdView.setMediaView(media_view);
        nativeAdView.setCallToActionView(callToActionView);
        nativeAdView.setHeadlineView(primaryView);
        secondaryView.setVisibility(VISIBLE);
        if (adHasOnlyStore(nativeAd)) {
            nativeAdView.setStoreView(secondaryView);
            secondaryText = store;
        } else if (!TextUtils.isEmpty(advertiser)) {
            nativeAdView.setAdvertiserView(secondaryView);
            secondaryText = advertiser;
        } else {
            secondaryText = "";
        }

        primaryView.setText(headline);
        callToActionView.setText(cta);
        if (blinksAdsButton.equals("true")) {
            YoYo.with(Techniques.Flash).duration(2000).repeat(1000).playOn(callToActionView);
        }
        //  Set the secondary view to be the star rating if available.
        if (starRating != null && starRating > 0) {
            secondaryView.setVisibility(GONE);
            ratingBar.setVisibility(VISIBLE);
            ratingBar.setRating(starRating.floatValue());

            nativeAdView.setStarRatingView(ratingBar);
        } else {
            secondaryView.setText(secondaryText);
            secondaryView.setVisibility(VISIBLE);
            ratingBar.setVisibility(GONE);
        }

        if (icon != null) {
            iconView.setVisibility(VISIBLE);
            iconView.setImageDrawable(icon.getDrawable());
        } else {
            iconView.setVisibility(GONE);
        }

        if (tertiaryView != null) {
            tertiaryView.setText(body);
            nativeAdView.setBodyView(tertiaryView);
        }

        nativeAdView.setNativeAd(nativeAd);
    }

    /**
     * To prevent memory leaks, make sure to destroy your ad when you don't need it anymore. This
     * method does not destroy the template view.
     * https://developers.google.com/admob/android/native-unified#destroy_ad
     */
    public void destroyNativeAd() {
        nativeAd.destroy();
    }

    public String getTemplateTypeName() {
        if (templateType == R.layout.gnt_medium_template_view) {
            return MEDIUM_TEMPLATE;
        } else if (templateType == R.layout.gnt_small_template_view) {
            return SMALL_TEMPLATE;
        }
        return "";
    }

    private void initView(Context context, AttributeSet attributeSet) {
        this.context = context;
        TypedArray attributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.TemplateView, 0, 0);

        try {
            templateType = attributes.getResourceId(R.styleable.TemplateView_gnt_template_type, R.layout.gnt_medium_template_view);
        } finally {
            attributes.recycle();
        }
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(templateType, this);
    }

    @Override
    public void onFinishInflate() {
        super.onFinishInflate();
        nativeAdView = findViewById(R.id.native_ad_view);
//        nativeAdView.setBackgroundColor(Color.parseColor(AdsKeys.Ads_Bg_Color));
        primaryView = findViewById(R.id.primary);
        secondaryView = findViewById(R.id.secondary);
        tertiaryView = findViewById(R.id.body);
        media_view = findViewById(R.id.media_view);

        ratingBar = findViewById(R.id.rating_bar);
        ratingBar.setEnabled(false);

        callToActionView = findViewById(R.id.cta);
        if (blinksAdsButton.equals("true")) {
            YoYo.with(Techniques.Flash).duration(2000).repeat(1000).playOn(callToActionView);
        }
//        callToActionView.setTextColor(Color.parseColor(AdsKeys.Btn_Text_Colo));
//        callToActionView.setBackgroundColor(Color.parseColor(AdsKeys.Btn_Bg_Color));
        ad_attribution = findViewById(R.id.ad_attribution);
//        ad_attribution.setTextColor(Color.parseColor(AdsKeys.Ads_Text_Color));
//        ad_attribution.getBackground().setColorFilter(Color.parseColor(AdsKeys.Ads_Bg_Color), PorterDuff.Mode.SRC_ATOP);
        iconView = findViewById(R.id.icon);
        background = findViewById(R.id.background);
    }
}
