package com.xhj.enjoy.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.leaf.library.StatusBarUtil

/**
 * Created by luyao
 * on 2019/5/31 16:16
 */
abstract class BaseVMActivity<VM : BaseViewModel> : AppCompatActivity(), LifecycleObserver {

    lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setDarkMode(this);
        initVM()
        startObserve()
        setContentView(getLayoutId())
        initView()
        initData()
    }

    abstract fun getLayoutId(): Int
    abstract fun initView()
    abstract fun initData()

    private fun initVM() {
        providerVMClass()?.let {
            mViewModel = ViewModelProvider(this).get(it)
            mViewModel.let(lifecycle::addObserver)
        }
    }

    open fun providerVMClass(): Class<VM>? = null


    open fun startObserve() {
        mViewModel.mException.observe(this, Observer { it?.let { onError(it) } })
    }

    open fun onError(e: Throwable) {}

    override fun onDestroy() {
        mViewModel.let {
            lifecycle.removeObserver(it)
        }
        super.onDestroy()
    }
}