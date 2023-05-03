package com.example.myforum.timber

import com.example.myforum.BuildConfig
import timber.log.Timber

object TimberInit {

    fun initTimber() {
        //在onCreate()中
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree());
        } else {
            Timber.plant(CrashReportingTree());
        }
    }

    private class CrashReportingTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {}
    }

}