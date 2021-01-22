package io.github.stonehiy.lib.net.manager

import android.arch.lifecycle.MutableLiveData


/**
 * 描述　: 网络变化管理者
 */
class NetworkStateManager private constructor() {

    val mNetworkStateCallback = MutableLiveData<NetState>()

    companion object {
        @JvmStatic
        val instance: NetworkStateManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkStateManager()
        }
    }

}