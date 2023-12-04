package com.nightavntrs.test

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment


class toolBar : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        var inflater = inflater.inflate(R.layout.fragment_tool_bar, container, false)
        var home_button = inflater.findViewById<ImageButton>(R.id.Home)
        var fav_button = inflater.findViewById<ImageButton>(R.id.Fav)
        var search_button = inflater.findViewById<ImageButton>(R.id.Search)
        var menu_button = inflater.findViewById<ImageButton>(R.id.Menu)
        if(curFrame == "home")
        {
            home_button.setImageResource(R.drawable.home_button)
            fav_button.setImageResource(R.drawable.fav_button_inactive)
            search_button.setImageResource(R.drawable.search_button_inactive)
            menu_button.setImageResource(R.drawable.menu_button_inactive)
        }
        if(curFrame == "fav")
        {
            home_button.setImageResource(R.drawable.home_button_inactive)
            fav_button.setImageResource(R.drawable.fav_button)
            search_button.setImageResource(R.drawable.search_button_inactive)
            menu_button.setImageResource(R.drawable.menu_button_inactive)
        }
        if(curFrame == "search")
        {
            home_button.setImageResource(R.drawable.home_button_inactive)
            fav_button.setImageResource(R.drawable.fav_button_inactive)
            search_button.setImageResource(R.drawable.search_button)
            menu_button.setImageResource(R.drawable.menu_button_inactive)
        }
        if(curFrame == "menu")
        {
            home_button.setImageResource(R.drawable.home_button_inactive)
            fav_button.setImageResource(R.drawable.fav_button_inactive)
            search_button.setImageResource(R.drawable.search_button_inactive)
            menu_button.setImageResource(R.drawable.menu_button)
        }
        //home_button.setImageResource(R.drawable.home_button)
        home_button.setOnClickListener {
            currentFrame("home")
            home_button.setImageResource(R.drawable.home_button)
            fav_button.setImageResource(R.drawable.fav_button_inactive)
            search_button.setImageResource(R.drawable.search_button_inactive)
            menu_button.setImageResource(R.drawable.menu_button_inactive)
        }
        fav_button.setOnClickListener {
            currentFrame("fav")
            home_button.setImageResource(R.drawable.home_button_inactive)
            fav_button.setImageResource(R.drawable.fav_button)
            search_button.setImageResource(R.drawable.search_button_inactive)
            menu_button.setImageResource(R.drawable.menu_button_inactive)

        }
        search_button.setOnClickListener {
            currentFrame("search")
            home_button.setImageResource(R.drawable.home_button_inactive)
            fav_button.setImageResource(R.drawable.fav_button_inactive)
            search_button.setImageResource(R.drawable.search_button)
            menu_button.setImageResource(R.drawable.menu_button_inactive)

        }
        menu_button.setOnClickListener {
            currentFrame("menu")
            home_button.setImageResource(R.drawable.home_button_inactive)
            fav_button.setImageResource(R.drawable.fav_button_inactive)
            search_button.setImageResource(R.drawable.search_button_inactive)
            menu_button.setImageResource(R.drawable.menu_button)
        }
        return inflater
    }
    fun currentFrame(nameFrame: String) {
        if(nameFrame == "home"){
            curFrame = "home"
        }
        if(nameFrame == "fav"){
            curFrame = "fav"
        }
        if(nameFrame == "search"){
            curFrame = "search"
        }
        if(nameFrame == "menu"){
            curFrame = "menu"
        }
    }
}