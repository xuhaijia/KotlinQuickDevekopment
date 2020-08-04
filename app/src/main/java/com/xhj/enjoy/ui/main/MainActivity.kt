package com.xhj.enjoy.ui.main

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.afollestad.assent.Permission
import com.afollestad.assent.askForPermissions
import com.xhj.enjoy.R
import com.xhj.enjoy.base.BaseVMActivity
import com.xhj.enjoy.view.BottomNavigationViewEx
import kotlinx.android.synthetic.main.acitivty_main.*


/**
 * Author: 7
 * Create: 2020/5/11 15:21
 */
class MainActivity : BaseVMActivity<MainViewModel>() {

    override fun providerVMClass(): Class<MainViewModel>? {
        return MainViewModel::class.java
    }
    override fun getLayoutId(): Int = R.layout.acitivty_main

    override fun initView() {
        askForPermissions(Permission.WRITE_EXTERNAL_STORAGE , Permission.CAMERA) {
        }

        val navController: NavController =
            Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(bottomNav, navController)

        bottomNav.itemIconTintList = null
        bottomNav.enableAnimation(false)
        bottomNav.setIconsMarginTop(BottomNavigationViewEx.dp2px(this, 6f))
    }

    override fun initData() {
    }

    override fun onNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp()
    }
}