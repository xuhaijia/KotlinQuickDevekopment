package com.xhj.enjoy

import android.app.Application
import android.content.ContextWrapper
import android.util.Log
import com.tencent.mmkv.MMKV


lateinit var mApplication: Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        mApplication = this

        val root = MMKV.initialize(this)
        // mmkv初始化
        Log.d("mmkv", root)

    }
}

object AppContext : ContextWrapper(mApplication)//ContextWrapper对Context上下文进行包装(装饰者模式)