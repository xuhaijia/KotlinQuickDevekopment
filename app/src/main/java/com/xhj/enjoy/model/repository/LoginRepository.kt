package com.xhj.enjoy.model.repository

import com.google.gson.Gson
import com.xhj.enjoy.App
import com.xhj.enjoy.core.Preference
import com.xhj.enjoy.model.api.BaseRepository
import com.xhj.enjoy.model.api.WanRetrofitClient
import com.xhj.enjoy.model.bean.User

import java.io.IOException
import com.xhj.enjoy.model.api.Result

class LoginRepository : BaseRepository() {

    private var isLogin by Preference(Preference.IS_LOGIN, false)
    private var userJson by Preference(Preference.USER_GSON, "")


    suspend fun login(userName: String, passWord: String): Result<User> {
//        return apiCall { WanRetrofitClient.service.login(userName, passWord) }
        return safeApiCall(
            call = { requestLogin(userName, passWord) },
            errorMessage = "登录失败!"
        )
    }

    // TODO Move into DataSource Layer ?
    private suspend fun requestLogin(userName: String, passWord: String): Result<User> {
        val response = WanRetrofitClient.service.login(userName, passWord)
        return if (response.errorCode != -1) {
            val user = response.data
            isLogin = true
            userJson = Gson().toJson(user)
//            App.CURRENT_USER = user
            Result.Success(user)
        } else {
            Result.Error(IOException(response.errorMsg))
        }
    }

    suspend fun register(userName: String, passWord: String): Result<User> {
//        return apiCall { WanRetrofitClient.service.register(userName, passWord, passWord) }
        return safeApiCall(call = { requestRegister(userName, passWord) }, errorMessage = "注册失败")
    }

    private suspend fun requestRegister(userName: String, passWord: String): Result<User> {
        val response = WanRetrofitClient.service.register(userName, passWord, passWord)
        return if (response.errorCode != -1) {
            requestLogin(userName, passWord)
        } else {
            Result.Error(IOException(response.errorMsg))
        }
    }

}