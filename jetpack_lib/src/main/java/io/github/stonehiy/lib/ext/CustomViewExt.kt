package io.github.stonehiy.lib.ext

import android.app.Activity
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.inputmethod.InputMethodManager

/**
 * 描述　:项目中自定义类的拓展函数
 */


//绑定普通的Recyclerview
fun RecyclerView.init(
        bindAdapter: RecyclerView.Adapter<*>,
        layoutManger: RecyclerView.LayoutManager? = null,
        isScroll: Boolean = true
): RecyclerView {
    if (null != layoutManger) {
        layoutManager = layoutManger
    }
    setHasFixedSize(true)
    adapter = bindAdapter
    isNestedScrollingEnabled = isScroll
    return this
}

//绑定SwipeRecyclerView
//fun SwipeRecyclerView.init(
//    layoutManger: RecyclerView.LayoutManager,
//    bindAdapter: RecyclerView.Adapter<*>,
//    isScroll: Boolean = true
//): SwipeRecyclerView {
//    layoutManager = layoutManger
//    setHasFixedSize(true)
//    adapter = bindAdapter
//    isNestedScrollingEnabled = isScroll
//    return this
//}

//fun SwipeRecyclerView.initFooter(loadmoreListener: SwipeRecyclerView.LoadMoreListener): DefineLoadMoreView {
//    val footerView = DefineLoadMoreView(appContext)
//    //给尾部设置颜色
//    footerView.setLoadViewColor(SettingUtil.getOneColorStateList(appContext))
//    //设置尾部点击回调
//    footerView.setmLoadMoreListener(SwipeRecyclerView.LoadMoreListener {
//        footerView.onLoading()
//        loadmoreListener.onLoadMore()
//    })
//    this.run {
//        //添加加载更多尾部
//        addFooterView(footerView)
//        setLoadMoreView(footerView)
//        //设置加载更多回调
//        setLoadMoreListener(loadmoreListener)
//    }
//    return footerView
//}


//初始化 SwipeRefreshLayout
fun SwipeRefreshLayout.init(onRefreshListener: () -> Unit) {
    this.run {
        setOnRefreshListener {
            onRefreshListener.invoke()
        }
        //设置主题颜色
//        setColorSchemeColors(SettingUtil.getColor(appContext))
    }
}

/**
 * 初始化普通的toolbar 只设置标题
 */
fun Toolbar.init(titleStr: String = ""): Toolbar {
//    setBackgroundColor(SettingUtil.getColor(appContext))
    title = titleStr
    return this
}

/**
 * 初始化有返回键的toolbar
 */
//fun Toolbar.initClose(
//    titleStr: String = "",
//    backImg: Int = R.drawable.ic_back,
//    onBack: (toolbar: Toolbar) -> Unit
//): Toolbar {
////    setBackgroundColor(SettingUtil.getColor(appContext))
//    title = titleStr.toHtml()
//    setNavigationIcon(backImg)
//    setNavigationOnClickListener { onBack.invoke(this) }
//    return this
//}


/**
 * 隐藏软键盘
 */
fun hideSoftKeyboard(activity: Activity?) {
    activity?.let { act ->
        val view = act.currentFocus
        view?.let {
            val inputMethodManager =
                    act.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(
                    view.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }
}


