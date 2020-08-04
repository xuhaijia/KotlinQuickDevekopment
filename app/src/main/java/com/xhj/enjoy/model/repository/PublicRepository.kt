package com.xhj.enjoy.model.repository

import com.xhj.enjoy.model.api.BaseRepository
import com.xhj.enjoy.model.api.GankRetrofitClient
import com.xhj.enjoy.model.bean.Gank

class PublicRepository : BaseRepository() {

    suspend fun getGankList(page: Int): GankResponse<List<Gank>> {
        return apiGankCall { GankRetrofitClient.service.getGankList(page) }
    }
}