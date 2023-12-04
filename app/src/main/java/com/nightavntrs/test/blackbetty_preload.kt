package com.nightavntrs.test

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.io.File


class blackbetty_preload : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blackbetty_preload)
        CurrentContext = this
        setToolBar()
    }
    fun load_comic(view: View)
    {
        val intent = Intent(this, blackbetty_1::class.java)
        overridePendingTransition(0,0)
        startActivity(intent)
        val fav_button = findViewById<ImageView>(R.id.fav)
        if(fav_black_betty)
        {
            fav_button.setImageResource(R.drawable.fav_button)
        }
        else
        {
            fav_button.setImageResource(R.drawable.fav_button_inactive)
        }
    }
    fun set_fav(view: View)
    {
        val fav_button = findViewById<ImageButton>(R.id.fav)
        fun isFileExists(file: File): Boolean {
            return file.exists() && !file.isDirectory
        }
        val file = File(filesDir, "blackbetty.fav")
        if (isFileExists(file)) {
             file.delete()
            fav_button.setImageResource(R.drawable.fav_button_inactive)
            fav_black_betty = false
        } else {
            file.writeText("fav")
            IO().saveFile(this, "blackbetty.fav", "fav")
            fav_button.setImageResource(R.drawable.fav_button)
            fav_black_betty = true
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