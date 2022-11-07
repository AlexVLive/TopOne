package com.alexvlive.topone

import android.app.Application
import com.alexvlive.topone.di.AppComponent
import com.alexvlive.topone.di.DaggerAppComponent

class MyApp: Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .context(context = this)
            .build()
    }
}