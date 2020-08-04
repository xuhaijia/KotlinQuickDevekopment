package com.xhj.enjoy.ui.public

import androidx.lifecycle.MutableLiveData
import com.xhj.enjoy.base.BaseViewModel
import com.xhj.enjoy.ext.errorToast
import com.xhj.enjoy.ext.executeResponse
import com.xhj.enjoy.model.api.GankRetrofitClient
import com.xhj.enjoy.model.bean.Gank
import com.xhj.enjoy.model.repository.HomeRepository
import com.xhj.enjoy.model.repository.PublicRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PublicViewModel : BaseViewModel() {

    private val repository by lazy { PublicRepository() }

    val mGank : MutableLiveData<List<Gank>> = MutableLiveData()

    fun getGankList(page: Int) {
        launch {
            val result = withContext(Dispatchers.IO) { repository.getGankList(page) }
            executeResponse(result , {mGank.value = result.data}, {})
        }

    }
}
