package com.adsnativetamplete.activity;

import android.content.DialogInterface;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.adsnativetamplete.AdsKeys;
import com.adsnativetamplete.NativePreferenceUtil;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;
import com.onesignal.OneSignal;

import eu.dkaratzas.android.inapp.update.Constants;
import eu.dkaratzas.android.inapp.update.InAppUpdateManager;
import eu.dkaratzas.android.inapp.update.InAppUpdateStatus;

public class NativeBaseActivity extends AppCompatActivity {

    private static final int REQ_CODE_VERSION_UPDATE = 530;
    private static final int RC_APP_UPDATE = 11;
    private static final String ONESIGNAL_APP_ID = AdsKeys.onesignalIds;

    public static boolean isSubriptionDone = false;
    private InAppUpdateManager inAppUpdateManager;
    private AppUpdateManager mAppUpdateManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(AdsKeys.onesignalIds);
        if (new NativePreferenceUtil(this).isRatingShow()) {
            inAppRating();
        }
        inAppUpdating();
    }

    private void inAppRating() {
        final ReviewManager manager = ReviewManagerFactory.create(this);
        Task<ReviewInfo> request = manager.requestReviewFlow();
        request.addOnCompleteListener(new OnCompleteListener<ReviewInfo>() {
            @Override
            public void onComplete(@NonNull Task<ReviewInfo> task) {
                if (task.isSuccessful()) {
                    // We can get the ReviewInfo object
                    ReviewInfo reviewInfo = task.getResult();
                    Task<Void> flow = manager.launchReviewFlow(NativeBaseActivity.this, reviewInfo);
                    flow.addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
                } else {
                    Log.d("inAppRating", "inAppRating_Else: ");
                }
            }
        });
    }

    private void inAppUpdating() {
        inAppUpdateManager = InAppUpdateManager.Builder(this, REQ_CODE_VERSION_UPDATE)
                .resumeUpdates(true) // Resume the update, if the update was stalled. Default is true
                .mode(Constants.UpdateMode.FLEXIBLE)
                .snackBarMessage("An update has just been downloaded.")
                .snackBarAction("RESTART")
                .handler(new InAppUpdateManager.InAppUpdateHandler() {
                    @Override
                    public void onInAppUpdateError(int code, Throwable error) {

                    }

                    @Override
                    public void onInAppUpdateStatus(InAppUpdateStatus status) {
                        if (status.isDownloaded()) {
                            new AlertDialog.Builder(NativeBaseActivity.this)
                                    .setTitle("Download Done")
                                    .setMessage("An update has just been downloaded.")
                                    .setPositiveButton("INSTALL", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (mAppUpdateManager != null) {
                                                inAppUpdateManager.completeUpdate();
                                            }
                                        }
                                    })
                                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    })
                                    .show();
                        }
                    }
                });

        inAppUpdateManager.checkForAppUpdate();

        mAppUpdateManager = AppUpdateManagerFactory.create(this);

        mAppUpdateManager.registerListener(installStateUpdatedListener);

        mAppUpdateManager.getAppUpdateInfo().addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
            @Override
            public void onSuccess(AppUpdateInfo appUpdateInfo) {
                if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                        && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE /*AppUpdateType.IMMEDIATE*/)) {
                    try {
                        mAppUpdateManager.startUpdateFlowForResult(appUpdateInfo, AppUpdateType.FLEXIBLE, NativeBaseActivity.this, RC_APP_UPDATE);
                    } catch (IntentSender.SendIntentException e) {
                        e.printStackTrace();
                    }
                } else if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
                    popupSnackbarForCompleteUpdate();
                } else {
                    Log.e(" Tag", "checkForAppUpdateAvailability: something else");
                }
            }
        });
    }    InstallStateUpdatedListener installStateUpdatedListener = new
            InstallStateUpdatedListener() {
                @Override
                public void onStateUpdate(InstallState state) {
                    if (state.installStatus() == InstallStatus.DOWNLOADED) {
                        //CHECK THIS if AppUpdateType.FLEXIBLE, otherwise you can skip
                        popupSnackbarForCompleteUpdate();
                    } else if (state.installStatus() == InstallStatus.INSTALLED) {
                        if (mAppUpdateManager != null) {
                            mAppUpdateManager.unregisterListener(installStateUpdatedListener);
                        }

                    } else {
                        Log.i("", "InstallStateUpdatedListener: state: " + state.installStatus());
                    }
                }
            };

    private void popupSnackbarForCompleteUpdate() {
        new AlertDialog.Builder(this)
                .setTitle("Update Version")
                .setMessage("This app's new version available, Do you want to update?")
                .setPositiveButton("INSTALL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mAppUpdateManager != null) {
                            mAppUpdateManager.completeUpdate();
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }
}
