package com.xhj.enjoy.ui.article

import com.xhj.enjoy.R
import com.xhj.enjoy.base.BaseVMFragment
import com.xhj.enjoy.ui.login.LoginViewModel

class ArticleFragment : BaseVMFragment<ArticleViewModel>() {

    override fun providerVMClass(): Class<ArticleViewModel>? {
        return ArticleViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_article
    }

    override fun initView() {
    }

    override fun initData() {
    }
}