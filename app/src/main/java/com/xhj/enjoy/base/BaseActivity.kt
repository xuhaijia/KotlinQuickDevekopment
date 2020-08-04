package com.xhj.enjoy.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel


abstract class BaseActivity : AppCompatActivity(), CoroutineScope by MainScope() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView()
//        setSupportActionBar(mToolbar)
        initData()
    }

    abstract fun getLayoutId(): Int
    abstract fun initView()
    abstract fun initData()

    protected fun startActivity(z: Class<*>) {
        startActivity(Intent(this, z))
    }

    protected fun startActivity(z: Class<*>, name: String, value: Boolean) {
        val intent = Intent(this, z).putExtra(name, value)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}