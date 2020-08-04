package com.xhj.enjoy.ui.system

import com.xhj.enjoy.R
import com.xhj.enjoy.base.BaseVMFragment

class SystemFragment : BaseVMFragment<SystemViewModel>() {

    override fun providerVMClass(): Class<SystemViewModel>? {
        return SystemViewModel::class.java
    }
    override fun getLayoutId(): Int = R.layout.fragment_system

    override fun initView() {
    }

    override fun initData() {
    }
}