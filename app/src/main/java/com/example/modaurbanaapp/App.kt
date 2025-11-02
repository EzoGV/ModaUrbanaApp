package com.example.modaurbanaapp

import android.app.Application
import com.example.modaurbanaapp.data.remote.RetrofitClient

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        RetrofitClient.init(this)
    }
}
