package io.github.devlcc.ptmobileappchallenge

import android.app.Application
import di.initKoinAndroid

class PTMobileAppChallengeApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        initKoinAndroid(
            context = this,
            isDebug = BuildConfig.DEBUG,
        )
    }
}