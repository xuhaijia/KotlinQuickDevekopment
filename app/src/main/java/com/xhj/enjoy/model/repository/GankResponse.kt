package com.xhj.enjoy.model.repository

data class GankResponse<out T>(val data : T , val status : Int)

