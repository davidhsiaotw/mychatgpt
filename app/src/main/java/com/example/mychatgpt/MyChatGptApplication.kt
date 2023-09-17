package com.example.mychatgpt

import android.app.Application
import com.example.mychatgpt.data.AppContainer
import com.example.mychatgpt.data.AppDataContainer

class MyChatGptApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}