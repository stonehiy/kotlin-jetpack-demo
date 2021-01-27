package io.github.stonehiy.lib.ext

import android.arch.lifecycle.MutableLiveData
import io.github.stonehiy.lib.core.fragment.BaseVmFragment
import io.github.stonehiy.lib.core.viewmodel.BaseViewModel
import io.github.stonehiy.lib.net.AppException
import io.github.stonehiy.lib.net.BaseResponse
import io.github.stonehiy.lib.net.ExceptionHandle
import io.github.stonehiy.lib.state.ResultState
import io.github.stonehiy.lib.state.paresException
import io.github.stonehiy.lib.state.paresResult
import kotlinx.coroutines.*
import io.github.stonehiy.lib.core.activity.BaseVmActivity
import timber.log.Timber


/**
 * 描述　:BaseViewModel请求协程封装
 */

/**
 * 显示页面状态，这里有个技巧，成功回调在第一个，其后两个带默认值的回调可省
 * @param resultState 接口返回值
 * @param onLoading 加载中
 * @param onSuccess 成功回调
 * @param onError 失败回调
 *
 */
fun <T> BaseVmActivity<*>.parseState(
        resultState: ResultState<T>,
        onSuccess: (T?) -> Unit,
        onError: ((AppException) -> Unit)? = null,
        onLoading: (() -> Unit)? = null
) {
    when (resultState) {
        is ResultState.Loading -> {
            showLoading(resultState.loadingMessage)
            onLoading?.run { this }
        }
        is ResultState.Success -> {
            dismissLoading()
            onSuccess(resultState.data)
        }
        is ResultState.Error -> {
            dismissLoading()
            onError?.run { this(resultState.error) }
        }
    }
}

/**
 * 显示页面状态，这里有个技巧，成功回调在第一个，其后两个带默认值的回调可省
 * @param resultState 接口返回值
 * @param onLoading 加载中
 * @param onSuccess 成功回调
 * @param onError 失败回调
 *
 */
fun <T> BaseVmFragment<*>.parseState(
        resultState: ResultState<T>,
        onSuccess: (T?) -> Unit,
        onError: ((AppException) -> Unit)? = null,
        onLoading: (() -> Unit)? = null
) {
    when (resultState) {
        is ResultState.Loading -> {
            showLoading(resultState.loadingMessage)
            onLoading?.invoke()
        }
        is ResultState.Success -> {
            dismissLoading()
            onSuccess(resultState.data)
        }
        is ResultState.Error -> {
            dismissLoading()
            onError?.run { this(resultState.error) }
        }
    }
}


/**
 * net request 不校验请求结果数据是否是成功
 * @param block 请求体方法
 * @param resultState 请求回调的ResultState数据
 * @param isShowDialog 是否显示加载框
 * @param loadingMessage 加载框提示内容
 */
fun <T> BaseViewModel.requestNoCheck(
        block: suspend () -> T,
        resultState: MutableLiveData<ResultState<T>>,
        isShowDialog: Boolean = false,
        loadingMessage: String = "请求网络中..."
): Job {
    return viewModelScope.launch {
        runCatching {
            if (isShowDialog) resultState.value = ResultState.onAppLoading(loadingMessage)
            //请求体
            block()
        }.onSuccess {
            resultState.paresResult(it)
        }.onFailure {
            resultState.paresException(it)
        }
    }
}

/**
 * 过滤服务器结果，失败抛异常
 * @param block 请求体方法，必须要用suspend关键字修饰
 * @param success 成功回调
 * @param error 失败回调 可不传
 * @param isShowDialog 是否显示加载框
 * @param loadingMessage 加载框提示内容
 */
fun <T> BaseViewModel.request(
        block: suspend () -> BaseResponse<T>,
        success: (T?) -> Unit,
        error: (AppException) -> Unit = {},
        showError: (AppException) -> Boolean = { true },
        reLogin: () -> Boolean = { true },
        isShowDialog: Boolean = true,
        loadingMessage: String = "请求网络中...",
        onTokenOut: ((String) -> Unit)? = null
): Job {
    //如果需要弹窗 通知Activity/fragment弹窗
    return viewModelScope.launch {
        runCatching {
            if (isShowDialog) loadingChange.showDialog.postValue(loadingMessage)
            //请求体
            block()
        }.onSuccess {
            //网络请求成功 关闭弹窗
            loadingChange.dismissDialog.postValue(false)
            runCatching {
                //校验请求结果码是否正确，不正确会抛出异常走下面的onFailure
                executeResponse(it, { t ->
                    success(t)
                }, {
                    val isLogin = reLogin()
                    if(isLogin){
                        hasLogin.reLogin.postValue("ReLogin")
                    }
                })
            }.onFailure { e ->
                //打印错误消息
                Timber.w(e)
                //失败回调
                val appException = ExceptionHandle.handleException(e)
                error(appException)
                if (showError(appException)) {
                    showErrorMessage.showErrorToast.postValue(appException.errorMsg)
                }
            }
        }.onFailure {
            //网络请求异常 关闭弹窗
            loadingChange.dismissDialog.postValue(false)
            //打印错误消息
            Timber.w(it)
            //失败回调
            val appException = ExceptionHandle.handleException(it)
            error(appException)
            if (showError(appException)) {
                showErrorMessage.showErrorToast.postValue(appException.errorMsg)
            }
        }
    }
}

/**
 *  不过滤请求结果
 * @param block 请求体 必须要用suspend关键字修饰
 * @param success 成功回调
 * @param error 失败回调 可不给
 * @param isShowDialog 是否显示加载框
 * @param loadingMessage 加载框提示内容
 */
fun <T> BaseViewModel.requestNoCheck(
        block: suspend () -> T,
        success: (T) -> Unit,
        error: (AppException) -> Unit = {},
        isShowDialog: Boolean = false,
        loadingMessage: String = "请求网络中..."
): Job {
    //如果需要弹窗 通知Activity/fragment弹窗
    if (isShowDialog) loadingChange.showDialog.postValue(loadingMessage)
    return viewModelScope.launch {
        runCatching {
            //请求体
            block()
        }.onSuccess {
            //网络请求成功 关闭弹窗
            loadingChange.dismissDialog.postValue(false)
            //成功回调
            success(it)
        }.onFailure {
            //网络请求异常 关闭弹窗
            loadingChange.dismissDialog.postValue(false)
            //打印错误消息
            //失败回调
            error(ExceptionHandle.handleException(it))
        }
    }
}

/**
 * 请求结果过滤，判断请求服务器请求结果是否成功，不成功则会抛出异常
 */
suspend fun <T> executeResponse(
        response: BaseResponse<T>,
        success: suspend CoroutineScope.(T?) -> Unit,
        reLogin: () -> Unit = {}
        ) {
    coroutineScope {
        when {
            response.isSuccess() -> {
                success(response.getResponseData())
            }
            response.reLogin() -> {
                reLogin()
            }
            else -> {
                throw AppException(
                        response.getResponseCode(),
                        response.getResponseMsg(),
                        response.getResponseMsg()
                )
            }
        }
    }
}

/**
 *  调用携程
 * @param block 操作耗时操作任务
 * @param success 成功回调
 * @param error 失败回调 可不给
 */
fun <T> BaseViewModel.launch(
        block: () -> T,
        success: (T) -> Unit,
        error: (Throwable) -> Unit = {}
) {
    viewModelScope.launch {
        runCatching {
            withContext(Dispatchers.IO) {
                block()
            }
        }.onSuccess {
            success(it)
        }.onFailure {
            error(it)
        }
    }
}
