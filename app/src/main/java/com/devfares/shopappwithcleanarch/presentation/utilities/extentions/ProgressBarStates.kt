package com.devfares.shopappwithcleanarch.presentation.utilities.extentions

import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.FragmentActivity
import com.devfares.shopappwithcleanarch.R

fun FragmentActivity.startLoading() {
    findViewById<ProgressBar>(R.id.progressBar).apply {
        visibility = View.VISIBLE
    }
}

fun FragmentActivity.stopLoading() {
    findViewById<ProgressBar>(R.id.progressBar).apply {
        visibility = View.GONE
    }
}