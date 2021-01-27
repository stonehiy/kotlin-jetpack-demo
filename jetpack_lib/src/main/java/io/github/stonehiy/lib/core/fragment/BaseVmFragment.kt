package io.github.stonehiy.lib.core.fragment

import android.app.Application
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.stonehiy.lib.core.viewmodel.BaseViewModel
import io.github.stonehiy.lib.ext.getViewModel
import io.github.stonehiy.lib.ext.getVmClazz
import io.github.stonehiy.lib.net.manager.NetState
import io.github.stonehiy.lib.net.manager.NetworkStateManager

/**
 * 描述　: ViewModelFragment基类，自动把ViewModel注入Fragment
 */
abstract class BaseVmFragment<VM : BaseViewModel> : Fragment() {

    //是否第一次加载
    private var isFirst: Boolean = true

    lateinit var mViewModel: VM

    lateinit var mActivity: AppCompatActivity

    /**
     * 当前Fragment绑定的视图布局
     */
    abstract fun layoutId(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId(), container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as AppCompatActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isFirst = true
        mViewModel = createViewModel()
        initView(savedInstanceState)
        createObserver()
        registorDefUIChange()
        initData()
    }

    /**
     * 网络变化监听 子类重写
     */
    open fun onNetworkStateChanged(netState: NetState?) {}

    /**
     * 创建viewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(this.requireActivity().application)).get(getVmClazz(this))
    }

    /**
     * 初始化view
     */
    abstract fun initView(savedInstanceState: Bundle?)

    /**
     * 懒加载
     */
    abstract fun lazyLoadData()

    /**
     * 创建观察者
     */
    abstract fun createObserver()

    override fun onResume() {
        super.onResume()
        onVisible()
    }

    /**
     * 是否需要懒加载
     */
    private fun onVisible() {
        if (lifecycle.currentState == Lifecycle.State.STARTED && isFirst) {
            //等待view加载后触发懒加载
            view?.post {
                lazyLoadData()
                //在Fragment中，只有懒加载过了才能开启网络变化监听
                NetworkStateManager.instance.mNetworkStateCallback.observe(
                    this,
                    Observer {
                        //不是首次订阅时调用方法，防止数据第一次监听错误
                        if (!isFirst) {
                            onNetworkStateChanged(it)
                        }
                    })
                isFirst = false
            }
        }
    }

    /**
     * Fragment执行onCreate后触发的方法
     */
    open fun initData() {}

    abstract fun showLoading(message: String? = "请求网络中...")

    abstract fun dismissLoading()
    abstract fun showError(message: String?)
    abstract fun reLogin()

    /**
     * 注册 UI 事件
     */
    private fun registorDefUIChange() {
        mViewModel.loadingChange.showDialog.observe(this, Observer {
            showLoading(it)
        })
        mViewModel.loadingChange.dismissDialog.observe(this, Observer {
            dismissLoading()
        })

        mViewModel.showErrorMessage.showErrorToast.observe(this, Observer {
            showError(it)

        })
        mViewModel.hasLogin.reLogin.observe(this, Observer {
            reLogin()
        })
    }

    /**
     * 将非该Fragment绑定的ViewModel添加 loading回调 防止出现请求时不显示 loading 弹窗bug
     * @param viewModels Array<out BaseViewModel>
     */
    protected fun addLoadingObserve(vararg viewModels: BaseViewModel) {
        viewModels.forEach { viewModel ->
            //显示弹窗
            viewModel.loadingChange.showDialog.observe(this, Observer {
                showLoading(it)
            })
            //关闭弹窗
            viewModel.loadingChange.dismissDialog.observe(this, Observer {
                dismissLoading()
            })
        }
    }

}