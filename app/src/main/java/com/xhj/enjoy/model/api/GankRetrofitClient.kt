package com.xhj.enjoy.model.api

import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.xhj.enjoy.AppContext
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File

object GankRetrofitClient : BaseRetrofitClient() {
    val service by lazy {
        GankRetrofitClient.getService(
            GankService::class.java,
            GankService.BASE_URL
        )
    }

    private val cookieJar by lazy { PersistentCookieJar(
        SetCookieCache(), SharedPrefsCookiePersistor(
            AppContext
        )
    ) }

    override fun handleBuilder(builder: OkHttpClient.Builder) {

        val httpCacheDirectory = File(AppContext.cacheDir, "responses")
        val cacheSize = 10 * 1024 * 1024L // 10 MiB
        val cache = Cache(httpCacheDirectory, cacheSize)
        builder.cache(cache)
            .cookieJar(cookieJar)
            .addInterceptor (Interceptor())
    }
}