package com.app.bank_misr.data.app

import android.app.Application
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

import com.app.bank_misr.presentation.MainActivity
import dagger.hilt.android.HiltAndroidApp
import kotlin.system.exitProcess

@HiltAndroidApp
class App : Application(), DefaultLifecycleObserver {

  private val tag = "App Status"
  private var isAppInForeground = true
  val debug by lazy { 0 != applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE }

  override fun onCreate() {
    super<Application>.onCreate()
    setUncaughtExceptionHandler()
  }

  override fun onStart(owner: LifecycleOwner) {
    super.onStart(owner)
    isAppInForeground = true
    Log.d(tag, "App In Foreground")
  }

  override fun onStop(owner: LifecycleOwner) {
    isAppInForeground = false
    Log.d(tag, "App In Background")
    super.onStop(owner)
  }

  private fun setUncaughtExceptionHandler() {
    Thread.setDefaultUncaughtExceptionHandler { _, throwable ->
      Log.e("UncaughtException", throwable.stackTraceToString())
      startActivity(Intent(this, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
        putExtra("Crashed", true)
      })
      exitProcess(1)
    }
  }
}