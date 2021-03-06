package io.github.stonehiy.lib.core.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.github.stonehiy.lib.net.coroutine.ViewModelCoroutineScope
import kotlinx.coroutines.cancel


/**
 * 描述　: ViewModel的基类 使用ViewModel类，放弃AndroidViewModel，原因：用处不大 完全有其他方式获取Application上下文
 */
open class BaseViewModel : ViewModel() {
    val viewModelScope = ViewModelCoroutineScope()

    val loadingChange: UiLoadingChange by lazy { UiLoadingChange() }
    val showErrorMessage: ShowErrorMessage by lazy { ShowErrorMessage() }
    val hasLogin: ReLogin by lazy { ReLogin() }

    /**
     * 内置封装好的可通知Activity/fragment 显示隐藏加载框 因为需要跟网络请求显示隐藏loading配套才加的，不然我加他个鸡儿加
     */
    inner class UiLoadingChange {
        //显示加载框
        val showDialog by lazy { MutableLiveData<String>() }

        //隐藏
        val dismissDialog by lazy { MutableLiveData<Boolean>() }
    }

    inner class ShowErrorMessage {
        val showErrorToast by lazy { MutableLiveData<String>() }
    }

    inner class ReLogin{
        val reLogin by lazy { MutableLiveData<String>() }
    }

    override fun onCleared() {
        super.onCleared()
        //当生命周期销毁时，结束所有子协程
        viewModelScope.cancel()

    }

}