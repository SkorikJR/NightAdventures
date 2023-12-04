package com.nightavntrs.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class batgirl_8 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_batgirl8)
        CurrentContext = this
        setToolBar()
    }
    private fun setToolBar()
    {
        val tb = toolBar()
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.toolBar, tb)
        ft.commit()
    }
    fun prev_page(view: View)
    {
        val intent = Intent(this, batgirl_1::class.java)
        overridePendingTransition(0,0)
        startActivity(intent)
    }
    fun preload_return(view: View)
    {
        val intent = Intent(this, homeActivity::class.java)
        overridePendingTransition(0,0)
        startActivity(intent)
    }
}