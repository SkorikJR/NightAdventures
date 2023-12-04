package com.nightavntrs.test

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.nightavntrs.test.BannerLoad.Companion.FB_BANNER_ID
import com.nightavntrs.test.databinding.ActivityMainBinding
import java.io.File
import java.nio.file.Files
import kotlin.concurrent.timer


var ModeOK: Boolean = false
var BannerLoader = false
@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var btnEnableDisableSDK: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        begin()
    }
    private fun begin() {
        val progressBar = binding.progressBarID
        binding.buttonID.setOnClickListener {
            val visibility = if (progressBar.visibility == View.GONE) View.VISIBLE else View.GONE
            progressBar.visibility = visibility
            val btnText =
                if (progressBar.visibility == View.GONE) "SHOW PROGRESSBAR" else "HIDE PROGRESSBAR"
            binding.buttonID.text = btnText
        }
        if (isOnline(this)){
               var counter = 0
               val i: Intent = Intent(this, homeActivity::class.java)
               timer(initialDelay = 1000L, period = 1000L) {
                   counter += 1
                   if(counter == 30 && !BannerLoader){
                       startActivity(i)
                   }
               }
               if(BannerLoader){
                   val i: Intent = Intent(this, BannerLoad::class.java)
                   startActivity(i)
               }
        }
        else{
            var counter = 0
            val i: Intent = Intent(this, noConnection::class.java)
            timer(initialDelay = 1000L, period = 1000L) {
                counter += 1
                if(counter == 5){
                    startActivity(i)
                }
            }
        }
    }
    private fun isOnline(context: Context): Boolean {
        val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
    private fun isFileExists(file: File): Boolean {
        return file.exists() && !file.isDirectory
    }

}

