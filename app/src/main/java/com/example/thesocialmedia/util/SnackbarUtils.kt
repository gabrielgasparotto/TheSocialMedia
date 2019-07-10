package com.example.thesocialmedia.util

import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.view.View
import com.example.thesocialmedia.R
import kotlinx.android.synthetic.main.activity_login.*

class SnackbarUtils {

    fun showSnack(msg: String, view: View, context: Context) {
        val snack = Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
        snack.view.setBackgroundColor(ContextCompat.getColor(context, R.color.black))
        snack.show()
    }

}