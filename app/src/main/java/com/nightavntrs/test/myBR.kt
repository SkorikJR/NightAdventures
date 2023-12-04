package com.nightavntrs.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment


class myBR : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val inflater = inflater.inflate(R.layout.fragment_my_b_r, container, false)
        var webView = inflater.findViewById<WebView>(R.id.BC)
        webView.getSettings().setJavaScriptEnabled(true)
        val html = "<script type=\"text/javascript\" src=\"https://www.gstatic.com/swiffy/v7.4/runtime.js\"></script>\n" +
                "<script type=\"text/javascript\" src=\"https://www.bestchange.com/images/banners/468x60-12.js\"></script>\n" +
                "<a target=\"_blank\" href=\"https://www.bestchange.com/?p=1295561\" title=\"Electronic money exchange rates rating &ndash; BestChange\"><span id=\"29FS3bErdSGEBh6733e5\" style=\"display: inline-block; width: 468px; height: 60px; background-color: #89b508; margin: 0; padding: 0; border: none; overflow: hidden\" onclick=\"return false\"></span></a>\n" +
                "<script type=\"text/javascript\">\n" +
                "var stage = new swiffy.Stage(document.getElementById('29FS3bErdSGEBh6733e5'), swiffyobject, {});\n" +
                "stage.setFlashVars('clickTAG=https://www.bestchange.com/?p=1295561');\n" +
                "stage.start();\n" +
                "</script>"
        webView.loadDataWithBaseURL(
            "https://hipop.info",
            html,
            "text/html",
            "UTF-8",
            null
        )

        return inflater
        webView.clearCache(true)
    }

}