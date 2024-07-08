package com.adsnativetamplete.Tools;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class AppSocialTools {
    private static AppSocialTools appSocialTools;

    public static AppSocialTools getInstance() {
        AppSocialTools appSocialTools2 = appSocialTools;
        if (appSocialTools2 != null) {
            return appSocialTools2;
        }
        AppSocialTools appSocialTools3 = new AppSocialTools();
        appSocialTools = appSocialTools3;
        return appSocialTools3;
    }



    public String getOdrkd() {
        return AppAdOrganizer.getInstance().getEncyption(AppAdOrganizer.getInstance().getMnkdr(), AppTimeHandler.getInstance().getOlfdg());
    }


}
