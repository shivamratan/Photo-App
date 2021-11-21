package com.shivamratan.photoapp.utils

import android.content.Context
import android.view.View
import android.widget.Toast

fun View.setVisible() {
    visibility = View.VISIBLE
}

fun View.setInvisible() {
    visibility = View.INVISIBLE
}

fun Context.showLongToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun Context.showShortToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()