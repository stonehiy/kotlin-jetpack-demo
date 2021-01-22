package io.github.stonehiy.lib.core

@Deprecated("")
interface IView {

    /**
     * 显示loading
     */
    fun showLoading()

    /**
     * 隐藏loading
     */
    fun hideLoading()

    /**
     * 显示吐司
     */
    fun showError(msg: String)

    /**
     * 跳转登录页面
     */
    fun reLoginActivity()

}