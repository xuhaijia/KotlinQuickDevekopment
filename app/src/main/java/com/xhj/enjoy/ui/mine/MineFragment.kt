package com.xhj.enjoy.ui.mine

import com.xhj.enjoy.R
import com.xhj.enjoy.base.BaseVMFragment
import com.xhj.enjoy.ui.main.MainViewModel

class MineFragment: BaseVMFragment<MineViewModel>() {
    override fun providerVMClass(): Class<MineViewModel>? {
        return MineViewModel::class.java
    }
    override fun getLayoutId(): Int {
        return R.layout.fragment_mine
    }

    override fun initView() {
    }

    override fun initData() {
    }
}