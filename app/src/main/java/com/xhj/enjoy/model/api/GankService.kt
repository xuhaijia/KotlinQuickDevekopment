package com.xhj.enjoy.model.api

import com.xhj.enjoy.model.bean.Gank
import com.xhj.enjoy.model.repository.GankResponse
import retrofit2.http.*

interface GankService {

    companion object {
        const val BASE_URL = "https://gank.io/api/v2/data/category/"
    }

    @GET("Girl/type/Girl/page/{pageCount}/count/10")
    suspend fun getGankList(@Path("pageCount") pageCount: Int): GankResponse<List<Gank>>

}