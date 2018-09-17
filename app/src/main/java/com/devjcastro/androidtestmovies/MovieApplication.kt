package com.devjcastro.androidtestmovies

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import com.devjcastro.androidtestmovies.utils.PreferencesUtils
import com.devjcastro.androidtestmovies.utils.Prefs
import io.realm.Realm

class MovieApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        context = getApplicationContext()

        Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(PreferencesUtils.KeyPreferences.PREFERENCE_NAME)
                .setUseDefaultSharedPreference(false)
                .build()

        PreferencesUtils.updateApiKey("51b6d3e433484ae65de2e3e73e92e939")
    }

    companion object {

        private var context: Context? = null

        fun getAppContext(): Context? {
            return context
        }
    }
}