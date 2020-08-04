package com.xhj.enjoy.ui.login

import android.app.ProgressDialog
import androidx.lifecycle.Observer
import com.xhj.enjoy.R
import com.xhj.enjoy.base.BaseVMActivity
import com.xhj.enjoy.databinding.ActivityLoginBinding
import com.xhj.enjoy.ext.errorToast
import com.xhj.enjoy.ext.startKtxActivity
import com.xhj.enjoy.ext.textWatcher
import com.xhj.enjoy.ui.main.MainActivity
import com.xhj.enjoy.util.contentView
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.title_layout.*

/**
 * Author: 7
 * Create: 2020/5/11 10:37
 */
class LoginActivity : BaseVMActivity<LoginViewModel>() {

    private val binding by contentView<LoginActivity, ActivityLoginBinding>(R.layout.activity_login)

    override fun providerVMClass(): Class<LoginViewModel>? {
        return LoginViewModel::class.java
    }


    override fun getLayoutId(): Int {
       return R.layout.activity_login
    }


    override fun initView() {
        binding.lifecycleOwner = this
        binding.viewModel = mViewModel

    }

    override fun initData() {
        mToolbar.setNavigationOnClickListener { onBackPressed() }
        login.setOnClickListener { login() }
        register.setOnClickListener { register() }
        passwordEt.textWatcher { afterTextChanged { mViewModel.loginDataChanged(userNameEt.text.toString(), passwordEt.text.toString()) } }
        userNameEt.textWatcher { afterTextChanged { mViewModel.loginDataChanged(userNameEt.text.toString(), passwordEt.text.toString()) } }
    }

    override fun startObserve() {
        mViewModel.apply {

            uiState.observe(this@LoginActivity, Observer {
                if (it.showProgress) showProgressDialog()

                it.showSuccess?.let {
                    dismissProgressDialog()
                    startKtxActivity<MainActivity>()
                    finish()
                }

                it.showError?.let { err ->
                    dismissProgressDialog()
                    errorToast(err)
                }
            })
        }

    }

    private fun login() {
        mViewModel.login(userNameEt.text.toString(), passwordEt.text.toString())
    }

    private fun register() {
        mViewModel.register(userNameEt.text.toString(), passwordEt.text.toString())
    }

    private var progressDialog: ProgressDialog? = null
    private fun showProgressDialog() {
        if (progressDialog == null)
            progressDialog = ProgressDialog(this)
        progressDialog?.show()
    }

    private fun dismissProgressDialog() {
        progressDialog?.dismiss()
    }
}