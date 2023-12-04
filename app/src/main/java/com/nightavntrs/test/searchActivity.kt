package com.nightavntrs.test

import CustomAdapter
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import kotlin.properties.Delegates

private var lv: ListView? = null
private var customeAdapter: CustomAdapter? = null
var imageModelSearchArrayList: ArrayList<ImageModel>? = null
class searchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        CurrentContext = this
        setToolBar()
        var textInputEditText  = this.findViewById<TextInputEditText>(R.id.Search)
        textInputEditText.addTextChangedListener { charSequence  ->
            searching(charSequence.toString())
        }

    }
    fun searching(searchText: String)
    {
        try{
            imageModelSearchArrayList!!.clear()
        }
        catch (e: NullPointerException){

        }
        //Создание перечня для поиска
        var index = 0
        var listing = ArrayList<String>(6)
        listing.add("Batgirl Adventures 2023".uppercase())
        listing.add("Black Betty 2023".uppercase())
        listing.add("Catwoman 2023".uppercase())
        listing.add("Grimm Fairy Tales 2017".uppercase())
        listing.add("Supergirl: Futures End 2014".uppercase())
        listing.add("Supergirl: Wings (2023)".uppercase())
        val filteredCats = listing.filter { it.contains(searchText.uppercase()) }
        filteredCats.forEach {
            Log.i("PRESSED", it)
            lv = findViewById(R.id.listView) as ListView
            //imageModelArrayList = populateList()
            if(it == "Batgirl Adventures 2023".uppercase())
            {
                var imageModel = ImageModel()
                imageModel.setNames("Batgirl Adventures 2023")
                imageModel.setIDs(1)
                imageModel.setImage_drawables(R.drawable.batgirl)
                //imageModelArrayList!!.add(imageModel)
                try{
                    imageModelSearchArrayList!!.add(imageModel)
                }
                catch(e: NullPointerException){
                    imageModelSearchArrayList = ArrayList<ImageModel>(1)
                    imageModelSearchArrayList!!.add(imageModel)
                }
            }
            if(it == "Black Betty 2023".uppercase())
            {
                var imageModel = ImageModel()
                imageModel.setNames("Black Betty 2023")
                imageModel.setIDs(2)
                imageModel.setImage_drawables(R.drawable.blackbetty)
                //imageModelArrayList!!.add(imageModel)
                try{
                    imageModelSearchArrayList!!.add(imageModel)
                }
                catch(e: NullPointerException){
                    imageModelSearchArrayList = ArrayList<ImageModel>(1)
                    imageModelSearchArrayList!!.add(imageModel)
                }
            }
            if(it == "Catwoman 2023".uppercase())
            {
                var imageModel = ImageModel()
                imageModel.setNames("Catwoman 2023")
                imageModel.setIDs(3)
                imageModel.setImage_drawables(R.drawable.catwoman)
                //imageModelArrayList!!.add(imageModel)
                try{
                    imageModelSearchArrayList!!.add(imageModel)
                }
                catch(e: NullPointerException){
                    imageModelSearchArrayList = ArrayList<ImageModel>(1)
                    imageModelSearchArrayList!!.add(imageModel)
                }
            }
            if(it == "Grimm Fairy Tales 2017".uppercase())
            {
                var imageModel = ImageModel()
                imageModel.setNames("Grimm Fairy Tales 2017")
                imageModel.setIDs(4)
                imageModel.setImage_drawables(R.drawable.grimm_fairy)
                imageModelSearchArrayList!!.add(imageModel)
            }
            if(it == "Supergirl: Futures End 2014".uppercase())
            {
                var imageModel = ImageModel()
                imageModel.setNames("Supergirl: Futures End 2014")
                imageModel.setIDs(5)
                imageModel.setImage_drawables(R.drawable.supergirl_futures_end)
                //imageModelArrayList!!.add(imageModel)
                try{
                    imageModelSearchArrayList!!.add(imageModel)
                }
                catch(e: NullPointerException){
                    imageModelSearchArrayList = ArrayList<ImageModel>(1)
                    imageModelSearchArrayList!!.add(imageModel)
                }
            }
            if(it == "Supergirl: Wings (2023)".uppercase())
            {
                var imageModel = ImageModel()
                imageModel.setNames("Supergirl: Wings (2023)")
                imageModel.setIDs(6)
                imageModel.setImage_drawables(R.drawable.supergirl_wings)
                //imageModelArrayList!!.add(imageModel)
                try{
                    imageModelSearchArrayList!!.add(imageModel)
                }
                catch(e: NullPointerException){
                    imageModelSearchArrayList = ArrayList<ImageModel>(1)
                    imageModelSearchArrayList!!.add(imageModel)
                }
            }
            customeAdapter = CustomAdapter(this, imageModelSearchArrayList!!)
            lv!!.adapter = customeAdapter
        }
    }
    @Override
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
}