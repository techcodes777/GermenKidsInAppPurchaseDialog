package com.adsnativetamplete.Tools;


import android.os.Build;
import android.widget.FrameLayout;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.nativead.NativeAd;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;

public class AppAdOrganizer {
    private final String mnkdr = "RiXj8UfVaJ6DXrHVlZ99QQ==";
    private final String mckfe = "pckg";
    private final String idkrld = "ZUrCLVDd4UReWl10MKBHXQ==";
    private final String plkdr = "appvalley";
    private final String oieirk = "aktech";
    private final String ierkdddr = "heop";
    private static AppAdOrganizer appAdOrganizer;
    public static InterstitialAd mInterstitialAd;
    public static Boolean unityAdsloaded=false;
    private int adAmbCount = 0;
    public int getAdAmbCount() {
        return ++adAmbCount;
    }
    private static final int customcount = 0;
    public static NativeAd nativeAds;
    public static AppAdOrganizer getInstance() {

        if (appAdOrganizer != null)
            return appAdOrganizer;
        else
            appAdOrganizer = new AppAdOrganizer();

        return appAdOrganizer;
    }


    private static final String TAG = "InterstitialAds";
    static InterstitialAd mInterstitialAdAdmob;
    FrameLayout bannerContainer;



    public String getMckfe() {
        return mckfe;
    }

    public String getIerkdddr() {
        return ierkdddr;
    }

    String getMnkdr() {
        return mnkdr;
    }


    String getEncyption(String keyEnc, String textEnc) {

        try {
            byte[] keyStart = "encryption key".getBytes();
            KeyGenerator kgen;

            kgen = KeyGenerator.getInstance("AES");

            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed(keyStart);
            kgen.init(128, sr);
            byte[] decryptedData = AppTimeHandler.decrypt(keyEnc, textEnc);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                return new String(decryptedData, StandardCharsets.UTF_8);
            else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                return new String(decryptedData, StandardCharsets.UTF_8);
            }

        } catch (NoSuchAlgorithmException ignored) {
        } catch (Exception ignored) {
        }
        return keyEnc;
    }

    String getIdkrld() {
        return idkrld;
    }


    String getOieirk() {
        return oieirk;
    }

    public String getFoied() {
        return AppTimeHandler.getInstance().getMcnjf() + plkdr + AppTimeHandler.getInstance().getPksdf();
    }
}
