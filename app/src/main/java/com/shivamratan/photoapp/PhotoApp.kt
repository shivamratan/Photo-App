package com.shivamratan.photoapp

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PhotoApp: Application() {

    companion object{
        lateinit var mDunzoTestApp: PhotoApp
        fun getApplicationContext(): Context {
            return mDunzoTestApp.applicationContext
        }
    }

    init {
        mDunzoTestApp = this
    }
}