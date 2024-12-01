package com.lloydsbanking.lloydstest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LloydsTestApp: Application() {

    override fun onCreate() {
        super.onCreate()
        android.util.Log.d("LloydsTestApp", "onCreate(): LloydsTestApp")
    }
}