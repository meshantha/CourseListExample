package com.kalum.dynamo.courses

/**
 * Created by Kalum Fernando on 12/10/2018.
 */

import android.app.Application
import android.content.Context
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.android.startKoin


class CoursesApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        startKoin(this, listOf(appModule))
        appContext = applicationContext
    }

    companion object {

        var appContext: Context? = null
            private set
    }

}