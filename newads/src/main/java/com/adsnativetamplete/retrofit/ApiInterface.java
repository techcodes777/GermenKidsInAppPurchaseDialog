package com.adsnativetamplete.retrofit;

import com.adsnativetamplete.retrofit.models.AdsDetailsResponse;
import com.adsnativetamplete.retrofit.models.Category;
import com.adsnativetamplete.retrofit.models.CategoryReq;
import com.adsnativetamplete.retrofit.models.appupdatesettings.AppUpdateResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("adSettingId")
    Call<AdsDetailsResponse> getAdsDetails(@Query("applicationMasterId") String request);

    @GET("appUpdateSettingId")
    Call<AppUpdateResponse> getAppUpdate(@Query("applicationMasterId") String request);

    @POST("categoryWiseApplicationMaster")
    Call<Category> getCategoryApp(@Body CategoryReq request);
}
