package com.nightavntrs.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AlertDialog

class PrivatePolicy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_private_policy)
        CurrentContext = this
        setToolBar()
        setBR()
    }
    private fun setToolBar()
    {
        val tb = toolBar()
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.toolBar, tb)
        ft.commit()
    }
    fun setBR()
    {
        var webView = this.findViewById<WebView>(R.id.Browser)
        webView.getSettings().setJavaScriptEnabled(true)
        webView.loadUrl("https://onaminorscale.sbs/web/13546/pp.html")
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