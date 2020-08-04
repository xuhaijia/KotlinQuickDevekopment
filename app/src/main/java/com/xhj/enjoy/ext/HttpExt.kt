package com.xhj.enjoy.ext

import com.xhj.enjoy.model.repository.GankResponse
import com.xhj.enjoy.model.repository.WanResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

suspend fun executeResponse(response: WanResponse<Any>, successBlock: suspend CoroutineScope.() -> Unit,
                            errorBlock: suspend CoroutineScope.() -> Unit) {
    coroutineScope {
        if (response.errorCode == -1) errorBlock()
        else successBlock()
    }
}

suspend fun executeResponse(response: GankResponse<Any>, successBlock: suspend CoroutineScope.() -> Unit,
                            errorBlock: suspend CoroutineScope.() -> Unit) {
    coroutineScope {
        if (response.status == -1) errorBlock()
        else successBlock()
    }
}