package com.xhj.enjoy.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xhj.enjoy.base.BaseViewModel
import com.xhj.enjoy.ext.errorToast
import com.xhj.enjoy.ext.executeResponse
import com.xhj.enjoy.model.api.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.xhj.enjoy.model.bean.ArticleList
import com.xhj.enjoy.model.bean.Banner
import com.xhj.enjoy.model.repository.HomeRepository
import kotlinx.coroutines.launch


class HomeViewModel : BaseViewModel() {
    private val repository by lazy { HomeRepository() }
    val mBanners: MutableLiveData<List<Banner>> = MutableLiveData()
    val mArticleList: MutableLiveData<ArticleList> = MutableLiveData()


    fun getBanners() {
        launch {
            val result = withContext(Dispatchers.IO) { repository.getBanners() }
            executeResponse(
                result,
                { mBanners.value = result.data },
                { errorToast(result.errorMsg) })
        }
    }

    fun getArticleList(page: Int) {
        launch {
            val result = withContext(Dispatchers.IO) { repository.getArticleList(page) }
            executeResponse(
                result,
                { mArticleList.value = result.data },
                { errorToast(result.errorMsg) })
        }
    }
}