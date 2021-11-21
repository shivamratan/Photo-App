package com.shivamratan.photoapp.utils

import android.os.SystemClock
import androidx.appcompat.widget.SearchView

abstract class DebounceQueryTextListener: SearchView.OnQueryTextListener {
    private var lastClickedMillis: Long = SystemClock.elapsedRealtime()
    private val THRESHOLD_MILLIS = 800L

    abstract fun onQueryDebounce(newText: String?)

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        val now = SystemClock.elapsedRealtime()
        if(now - lastClickedMillis > THRESHOLD_MILLIS) {
            onQueryDebounce(newText)
        }

        lastClickedMillis = now
        return true
    }
}