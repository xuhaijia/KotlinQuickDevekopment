package com.xhj.enjoy.model.repository

import com.xhj.enjoy.model.api.BaseRepository
import com.xhj.enjoy.model.api.WanRetrofitClient
import com.xhj.enjoy.model.repository.WanResponse
import com.xhj.enjoy.model.bean.ArticleList
import com.xhj.enjoy.model.bean.Banner


class HomeRepository : BaseRepository() {

    suspend fun getBanners(): WanResponse<List<Banner>> {
        return apiCall { WanRetrofitClient.service.getBanner() }
    }

    suspend fun getArticleList(page: Int): WanResponse<ArticleList> {
        return apiCall { WanRetrofitClient.service.getHomeArticles(page) }
    }

    suspend fun collectArticle(articleId: Int): WanResponse<ArticleList> {
        return apiCall { WanRetrofitClient.service.collectArticle(articleId) }
    }

    suspend fun unCollectArticle(articleId: Int): WanResponse<ArticleList> {
        return apiCall { WanRetrofitClient.service.cancelCollectArticle(articleId) }
    }
}