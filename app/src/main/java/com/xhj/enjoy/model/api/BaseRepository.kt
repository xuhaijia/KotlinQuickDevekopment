package com.xhj.enjoy.model.api


import com.xhj.enjoy.model.repository.GankResponse
import com.xhj.enjoy.model.repository.WanResponse
import java.io.IOException


open class BaseRepository {

    suspend fun <T : Any> apiCall(call: suspend () -> WanResponse<T>): WanResponse<T> {
        return call.invoke()
    }

    suspend fun <T : Any> apiGankCall(call: suspend () -> GankResponse<T>): GankResponse<T> {
        return call.invoke()
    }

    suspend fun <T : Any> safeApiCall(call: suspend () -> Result<T>, errorMessage: String): Result<T> {
        return try {
            call()
        } catch (e: Exception) {
            // An exception was thrown when calling the API so we're converting this to an IOException
            Result.Error(IOException(errorMessage, e))
        }
    }


}