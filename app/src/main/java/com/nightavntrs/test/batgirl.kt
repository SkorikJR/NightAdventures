package com.nightavntrs.test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import java.io.File

class batgirl : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflater = inflater.inflate(R.layout.batgirl_fragment, container, false)
        val fav_button = inflater.findViewById<ImageView>(R.id.fav)
        check_fav(fav_button)
        return inflater
    }
    fun check_fav(view: ImageView)
    {
        if(fav_batgirl)
        {
            view.setImageResource(R.drawable.fav_button)
        }
        else
        {
            view.setImageResource(R.drawable.fav_button_inactive)
        }
    }
}