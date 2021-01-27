package com.stonehiy.jetpackdemo.base

import android.databinding.ViewDataBinding
import android.os.Bundle
import io.github.stonehiy.lib.core.activity.BaseVmDbActivity
import io.github.stonehiy.lib.core.appContext
import io.github.stonehiy.lib.core.viewmodel.BaseViewModel
import io.github.stonehiy.lib.util.ToastUtil

/**
 * 描述　: 你项目中的Activity基类，在这里实现显示弹窗，吐司，还有加入自己的需求操作 ，如果不想用Databind，请继承
 * BaseVmActivity例如
 * abstract class BaseActivity<VM : BaseViewModel> : BaseVmActivity<VM>() {
 */
abstract class CoreActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmDbActivity<VM, DB>(){
    abstract override fun layoutId(): Int


    abstract  override fun initView(savedInstanceState: Bundle?)

    override fun showLoading(message: String?) {
        ToastUtil.show(message)
    }

    override fun dismissLoading() {
        ToastUtil.show("dismissLoading")
    }

    override fun createObserver() {

    }

    override fun reLogin() {
    }


}