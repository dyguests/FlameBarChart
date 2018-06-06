package com.fanhl.flamebarchart.sample

import android.app.Application
import com.fanhl.flamebarchart.sample.util.BlankjUtils

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        BlankjUtils.init(this)
    }
}
