package com.example.thesocialmedia.util

import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.view.View
import com.example.thesocialmedia.R

class SnackbarUtils {

    fun showSnack(msg: String, referenceView: View, context: Context) {
        val snack = Snackbar.make(referenceView, msg, Snackbar.LENGTH_LONG)
        snack.view.setBackgroundColor(ContextCompat.getColor(context, R.color.black))
        snack.show()
    }

}