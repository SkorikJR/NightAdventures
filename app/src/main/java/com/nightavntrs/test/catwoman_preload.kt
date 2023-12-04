package com.nightavntrs.test

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.io.File


class catwoman_preload : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catwoman_preload)
        CurrentContext = this
        setToolBar()
        val fav_button = findViewById<ImageView>(R.id.fav)
        if(fav_catwoman)
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
        val intent = Intent(this, catwoman_1::class.java)
        overridePendingTransition(0,0)
        startActivity(intent)
    }
    fun set_fav(view: View)
    {
        val fav_button = findViewById<ImageButton>(R.id.fav)
        fun isFileExists(file: File): Boolean {
            return file.exists() && !file.isDirectory
        }
        val file = File(filesDir, "catwoman.fav")
        if (isFileExists(file)) {
            file.delete()
            fav_button.setImageResource(R.drawable.fav_button_inactive)
            fav_catwoman = false
        } else {
            IO().saveFile(this, "catwoman.fav", "fav")
            //file.writeText("fav")
            fav_button.setImageResource(R.drawable.fav_button)
            fav_catwoman = true
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