package com.nightavntrs.test

import CustomAdapter
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

private val a = 5
private val b = 10
private var lv: ListView? = null
private var customeAdapter: CustomAdapter? = null
var imageModelArrayList: ArrayList<ImageModel>? = null
private val myImageList = intArrayOf(R.drawable.batgirl, R.drawable.blackbetty, R.drawable.catwoman, R.drawable.grimm_fairy, R.drawable.supergirl_futures_end, R.drawable.supergirl_wings)
private val myImageNameList = arrayOf("Benz", "Bike", "Car", "Carrera", "Ferrari", "Harly")


class favActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        CurrentContext = this
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fav)
        setToolBar()
        drawing()
    }
    private fun drawing()
    {
        //Выводим список
        lv = findViewById(R.id.listView) as ListView
        //imageModelArrayList = populateList()
        if(fav_batgirl)
        {
            var imageModel = ImageModel()
            imageModel.setNames("Batgirl Adventures 2023")
            imageModel.setIDs(1)
            imageModel.setImage_drawables(R.drawable.batgirl)
            //imageModelArrayList!!.add(imageModel)
            try{
                imageModelArrayList!!.add(imageModel)
            }
            catch(e: NullPointerException){
                imageModelArrayList = ArrayList<ImageModel>(1)
                imageModelArrayList!!.add(imageModel)
            }
        }
        if(fav_black_betty)
        {
            var imageModel = ImageModel()
            imageModel.setNames("Black Betty 2023")
            imageModel.setIDs(2)
            imageModel.setImage_drawables(R.drawable.blackbetty)
            //imageModelArrayList!!.add(imageModel)
            try{
                imageModelArrayList!!.add(imageModel)
            }
            catch(e: NullPointerException){
                imageModelArrayList = ArrayList<ImageModel>(1)
                imageModelArrayList!!.add(imageModel)
            }
        }
        if(fav_catwoman)
        {
            var imageModel = ImageModel()
            imageModel.setNames("Catwoman 2023")
            imageModel.setIDs(3)
            imageModel.setImage_drawables(R.drawable.catwoman)
            //imageModelArrayList!!.add(imageModel)
            try{
                imageModelArrayList!!.add(imageModel)
            }
            catch(e: NullPointerException){
                imageModelArrayList = ArrayList<ImageModel>(1)
                imageModelArrayList!!.add(imageModel)
            }
        }
        if(fav_grimm_fairy)
        {
            var imageModel = ImageModel()
            imageModel.setNames("Grimm Fairy Tales 2017")
            imageModel.setIDs(4)
            imageModel.setImage_drawables(R.drawable.grimm_fairy)
            imageModelArrayList!!.add(imageModel)
        }
        if(fav_supergirl_futures_end)
        {
            var imageModel = ImageModel()
            imageModel.setNames("Supergirl: Futures End 2014")
            imageModel.setIDs(5)
            imageModel.setImage_drawables(R.drawable.supergirl_futures_end)
            //imageModelArrayList!!.add(imageModel)
            try{
                imageModelArrayList!!.add(imageModel)
            }
            catch(e: NullPointerException){
                imageModelArrayList = ArrayList<ImageModel>(1)
                imageModelArrayList!!.add(imageModel)
            }
        }
        if(fav_supergirl_wings)
        {
            var imageModel = ImageModel()
            imageModel.setNames("Supergirl: Wings (2023)")
            imageModel.setIDs(6)
            imageModel.setImage_drawables(R.drawable.supergirl_wings)
            //imageModelArrayList!!.add(imageModel)
            try{
                imageModelArrayList!!.add(imageModel)
            }
            catch(e: NullPointerException){
                imageModelArrayList = ArrayList<ImageModel>(1)
                imageModelArrayList!!.add(imageModel)
            }
        }
        customeAdapter = CustomAdapter(this, imageModelArrayList!!)
        lv!!.adapter = customeAdapter
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
            builder.setTitle("Really go out?")
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
    fun OnClick(view: View)
    {
        if(view.id == 1)
        {
            val intent = Intent(this, batgirl_preload::class.java)
            overridePendingTransition(0,0)
            startActivity(intent)
        }
        if(view.id == 2)
        {
            val intent = Intent(this, blackbetty_preload::class.java)
            overridePendingTransition(0,0)
            startActivity(intent)
        }
        if(view.id == 3)
        {
            val intent = Intent(this, catwoman_preload::class.java)
            overridePendingTransition(0,0)
            startActivity(intent)
        }
        if(view.id == 4)
        {
            val intent = Intent(this, grimm_fairy_preload::class.java)
            overridePendingTransition(0,0)
            startActivity(intent)
        }
        if(view.id == 5)
        {
            val intent = Intent(this, supergirl_futures_end_preload::class.java)
            overridePendingTransition(0,0)
            startActivity(intent)
        }
        if(view.id == 6)
        {
            val intent = Intent(this, supergirl_wings_preloader::class.java)
            overridePendingTransition(0,0)
            startActivity(intent)
        }
        imageModelArrayList!!.clear()
        Log.i("PRESSED", view.id.toString())
    }
}