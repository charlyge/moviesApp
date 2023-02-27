package com.charlyge.moviesapp.util

import android.app.ProgressDialog
import android.content.Context
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar

class ProgressBarUtil {

     fun initProgressBar(context: Context,title:String,message:String): ProgressDialog {
         val progressDialog = ProgressDialog(context)
         progressDialog.setTitle(title)
         progressDialog.setMessage(message)
         progressDialog.setCancelable(false)
        return progressDialog
    }
}