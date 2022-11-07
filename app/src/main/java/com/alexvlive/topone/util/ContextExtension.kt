package com.alexvlive.topone.util

import android.content.Context
import com.alexvlive.topone.MyApp
import com.alexvlive.topone.di.AppComponent

val Context.appComponent: AppComponent
    get() = when (this){
        is MyApp -> appComponent
        else -> this.applicationContext.appComponent
    }
