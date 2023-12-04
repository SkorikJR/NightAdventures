package com.nightavntrs.test

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import kotlin.properties.Delegates


var fav_black_betty: Boolean = false
var fav_batgirl: Boolean = false
var fav_catwoman: Boolean = false
var fav_grimm_fairy: Boolean = false
var fav_supergirl_futures_end: Boolean = false
var fav_supergirl_wings: Boolean = false

//Слушатель смены переменной curFrame
var CurrentContext: Context = homeActivity()
var curFrame: String by Delegates.observable("home") {property, oldValue, newValue -> homeActivity.frame_ch_toolbar().begin(newValue,CurrentContext)}
class homeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        CurrentContext = this
        super.onCreate(savedInstanceState)
        favFind()
        init_app()
        //BC_init()
    }
    //Открываем первый комикс
    public fun Load_black_betty(view: View){
        val intent = Intent(this, blackbetty_preload::class.java)
        startActivity(intent)
    }
    public fun Load_batgirl(view: View){
        val intent = Intent(this, batgirl_preload::class.java)
        startActivity(intent)
    }
    public fun Load_catwoman(view: View){
        val intent = Intent(this, catwoman_preload::class.java)
        startActivity(intent)
    }
    public fun Load_grimm_fairy(view: View){
        val intent = Intent(this, grimm_fairy_preload::class.java)
        startActivity(intent)
    }
    public fun Load_supergirl_futures_end(view: View){
        val intent = Intent(this, supergirl_futures_end_preload::class.java)
        startActivity(intent)
    }
    public fun Load_supergirl_wings(view: View){
        val intent = Intent(this, supergirl_wings_preloader::class.java)
        startActivity(intent)
    }
    fun isFileExists(file: File): Boolean {
        return file.exists() && !file.isDirectory
    }
    fun favFind()
    {
        val fileblackbetty = File(filesDir,"blackbetty.fav")
        if (isFileExists(fileblackbetty)) {
            fav_black_betty = true
        } else {
            //TODO
        }
        val filebatgirl = File(filesDir,"batgirl.fav")
        if (isFileExists(filebatgirl)) {
            fav_batgirl = true
        } else {
            //TODO
        }
        val filecatwoman = File(filesDir,"catwoman.fav")
        if (isFileExists(filecatwoman)) {
            fav_catwoman = true
        } else {
            //TODO
        }
        val filegrimm_fairy = File(filesDir,"grimm_fairy.fav")
        if (isFileExists(filegrimm_fairy)) {
            fav_grimm_fairy = true
        } else {
            //TODO
        }
        val filesupergirl_futures_end = File(filesDir,"supergirl_futures_end.fav")
        if (isFileExists(filesupergirl_futures_end)) {
            fav_supergirl_futures_end = true
        } else {
            //TODO
        }
        val filesupergirl_wings = File(filesDir,"supergirl_wings.fav")
        if (isFileExists(filesupergirl_wings)) {
            fav_supergirl_wings = true
        } else {
            //TODO
        }
    }
    private fun init_app()
    {
        setContentView(R.layout.activity_home)
        setToolBar()
        Grimm_Fairy()
        Black_Betty()
        Supergirl_Wings()
        Catwomen()
        Batgirl_Adventures()
        Supergirl_Futures_End()
    }
    private fun Black_Betty() {
        val tb = blackbetty()
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.black_betty_container, tb)
        ft.commit()
    }
    private fun BC_init() {
        val tb = myBR()
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.BC, tb)
        ft.commit()
    }
    private fun Supergirl_Wings() {
        val tb = Supergirl_wings()
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.supergirl_wings_container, tb)
        ft.commit()
    }
    private fun Catwomen() {
        val tb = catwoman()
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.catwoman_container, tb)
        ft.commit()
    }
    private fun Batgirl_Adventures() {
        val tb = batgirl()
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.batgirl_adventures_container, tb)
        ft.commit()
    }
    private fun Supergirl_Futures_End() {
        val tb = supergirl_futures_end()
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.supergirl_futures_end_container, tb)
        ft.commit()
    }
    private fun Grimm_Fairy() {
        val tb = grimm_fairy()
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.grimm_fair_container, tb)
        ft.commit()
    }
    private fun setToolBar()
    {
        val tb = toolBar()
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.toolBar, tb)
        ft.commit()
    }
    @Override
    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
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
            supportFragmentManager.popBackStack()
        }
    }
    class frame_ch_toolbar
    {
        fun begin(currFrame: String,context: Context)
        {
            if(currFrame == "home")
            {
                val intent = Intent(context, homeActivity::class.java)
                context.startActivity(intent)
            }
            if(currFrame == "fav")
            {
                if(!fav_black_betty&&!fav_batgirl&&!fav_catwoman&&!fav_grimm_fairy&&!fav_supergirl_futures_end&&!fav_supergirl_wings)
                {
                    val intent = Intent(context, favActivity_empty::class.java)
                    context.startActivity(intent)
                }
                else
                {
                    val intent = Intent(context, favActivity::class.java)
                    context.startActivity(intent)
                    try{
                        imageModelArrayList!!.clear()
                    }
                    catch (e: NullPointerException){

                    }
                }
            }
            if(currFrame == "search")
            {
                val intent = Intent(context, searchActivity::class.java)
                context.startActivity(intent)
            }
            if(currFrame == "menu")
            {
                val intent = Intent(context, MainMenu::class.java)
                context.startActivity(intent)
            }
        }
    }
}

