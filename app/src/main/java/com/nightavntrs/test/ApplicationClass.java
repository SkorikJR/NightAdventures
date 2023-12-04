package com.nightavntrs.test;

import static androidx.core.content.PackageManagerCompat.LOG_TAG;

import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;
import com.facebook.FacebookSdk;
import com.facebook.applinks.AppLinkData;
import com.google.firebase.FirebaseApp;
import com.onesignal.OneSignal;
import com.onesignal.debug.LogLevel;
import com.onesignal.Continue;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.RemoteException;
import android.util.Log;

import bolts.AppLinks;

public class ApplicationClass extends Application {
    InstallReferrerClient referrerClient;
    private static final String ONESIGNAL_APP_ID = "3c319585-c143-48ec-971a-9bcafbaf9c32";
    @Override
    public void onCreate() {
        FirebaseApp.initializeApp(this);
        //FaceBook
        FacebookSdk.sdkInitialize(this);
        FacebookSdk.setAutoInitEnabled(true);
        FacebookSdk.setAdvertiserIDCollectionEnabled(true);
        FacebookSdk.setAutoLogAppEventsEnabled(true);
        FacebookSdk.fullyInitialize();
        AppLinkData.fetchDeferredAppLinkData(this,
                new AppLinkData.CompletionHandler() {
                    @Override
                    public void onDeferredAppLinkDataFetched(AppLinkData appLinkData) {
                        try{
                            Log.d("Facebook", "App Link Target URL: " + appLinkData.getTargetUri().toString());
                            //new IO().saveFileFromClass(getApplicationContext(),"FB_BANNER_ID.cfg", appLinkData.getTargetUri().toString().replace("app://",""));
                            BannerLoad.FB_BANNER_ID = appLinkData.getTargetUri().toString().replace("app://","");
                            //Log.d("Facebook", "Save`d Target URL");
                        }
                        catch (NullPointerException e)
                        {
                            Log.d("Facebook", "Link is NULL");
                        }
                    }
                }
        );
        //OneSignal
        OneSignal.getDebug().setLogLevel(LogLevel.VERBOSE);
        OneSignal.initWithContext(this, ONESIGNAL_APP_ID);
        OneSignal.getNotifications().requestPermission(true, Continue.with(r -> {
            if (r.isSuccess()) {
                if (r.getData()) {
                    // `requestPermission` completed successfully and the user has accepted permission
                    Log.d("[!]", "Notifications permission`s granted!");
                }
                else {
                    // `requestPermission` completed successfully but the user has rejected permission
                    Log.d("[!]", "Notifications permission`s NOT granted!");
                }
            }
            else {
                // `requestPermission` completed unsuccessfully, check `r.getThrowable()` for more info on the failure reason
                Log.d("example", "Notifications permission`s query ERROR!");
            }
        }));
        //google referrer----------------------------------
        InstallReferrerClient referrerClient;

        referrerClient = InstallReferrerClient.newBuilder(this).build();
        referrerClient.startConnection(new InstallReferrerStateListener() {
            @Override
            public void onInstallReferrerSetupFinished(int responseCode) {
                switch (responseCode) {
                    case InstallReferrerClient.InstallReferrerResponse.OK:
                        ReferrerDetails response = null;
                        try {
                            response = referrerClient.getInstallReferrer();
                        } catch (RemoteException e) {
                            throw new RuntimeException(e);
                        }
                        String referrerUrl = response.getInstallReferrer();
                        Log.i("[GOOGLE]",referrerUrl.toString());
                        BannerLoad.GOOGLE_REF = referrerUrl.toString();
                        long referrerClickTime = response.getReferrerClickTimestampSeconds();
                        long appInstallTime = response.getInstallBeginTimestampSeconds();
                        boolean instantExperienceLaunched = response.getGooglePlayInstantParam();
                        break;
                    case InstallReferrerClient.InstallReferrerResponse.FEATURE_NOT_SUPPORTED:
                        // API not available on the current Play Store app.
                        break;
                    case InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE:
                        // Connection couldn't be established.
                        break;
                }
            }
            @Override
            public void onInstallReferrerServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
            }
        });
        super.onCreate();
    }
}
