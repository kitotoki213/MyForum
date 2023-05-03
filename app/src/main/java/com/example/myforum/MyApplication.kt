package com.example.myforum

import android.app.Application
import com.example.myforum.preference.MyPreference
import com.example.myforum.timber.TimberInit
import timber.log.Timber

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        TimberInit.initTimber()
        MyPreference.initMySharePreference(this.applicationContext)
    }
}