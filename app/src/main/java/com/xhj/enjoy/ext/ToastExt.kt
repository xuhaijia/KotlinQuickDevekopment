package com.xhj.enjoy.ext

import android.widget.Toast
import com.xhj.enjoy.AppContext
import es.dmoral.toasty.Toasty

fun successToast(success: String) {
    Toasty.success(AppContext, success, Toast.LENGTH_SHORT, true).show()
}

fun successToast(success: Int) {
    Toasty.success(AppContext, success, Toast.LENGTH_SHORT, true).show()
}

fun warningToast(warning: String) {
    Toasty.warning(AppContext, warning, Toast.LENGTH_SHORT, true).show()
}

fun warningToast(warning: Int) {
    Toasty.warning(AppContext, warning, Toast.LENGTH_SHORT, true).show()
}

fun errorToast(error: String) {
    Toasty.error(AppContext, error, Toast.LENGTH_SHORT, true).show();
}

fun errorToast(error: Int) {
    Toasty.error(AppContext, error, Toast.LENGTH_SHORT, true).show();
}
