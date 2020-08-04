package com.xhj.enjoy.ext

import com.xhj.enjoy.AppContext

fun getVersionName(): String {
    return AppContext.packageManager.getPackageInfo(AppContext.packageName, 0).versionName
}