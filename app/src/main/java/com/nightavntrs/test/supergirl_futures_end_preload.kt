package com.nightavntrs.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import java.io.File

class supergirl_futures_end_preload : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_supergirl_futures_end_preload)
        CurrentContext = this
        setToolBar()
        val fav_button = findViewById<ImageView>(R.id.fav)
        if(fav_supergirl_futures_end)
        {
            fav_button.setImageResource(R.drawable.fav_button)
        }
        else
        {
            fav_button.setImageResource(R.drawable.fav_button_inactive)
        }
    }
    fun load_comic(view: View)
    {
        val intent = Intent(this, supergirl_futures_end_1::class.java)
        overridePendingTransition(0,0)
        startActivity(intent)
    }
    fun set_fav(view: View)
    {
        val fav_button = findViewById<ImageButton>(R.id.fav)
        fun isFileExists(file: File): Boolean {
            return file.exists() && !file.isDirectory
        }
        val file = File(filesDir, "supergirl_futures.fav")
        if (isFileExists(file)) {
            file.delete()
            fav_button.setImageResource(R.drawable.fav_button_inactive)
            fav_supergirl_futures_end = false
        } else {
            //file.writeText("fav")
            IO().saveFile(this, "supergirl_futures.fav", "fav")
            fav_button.setImageResource(R.drawable.fav_button)
            fav_supergirl_futures_end = true
        }
    }
    private fun setToolBar()
    {
        val tb = toolBar()
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.toolBar, tb)
        ft.commit()
    }
}