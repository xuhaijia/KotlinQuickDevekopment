package com.xhj.enjoy.model.repository


data class WanResponse<out T>(val errorCode: Int, val errorMsg: String, val data: T)