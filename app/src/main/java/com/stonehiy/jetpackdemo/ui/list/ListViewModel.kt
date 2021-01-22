package com.stonehiy.jetpackdemo.ui.list


import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.qhebusbar.basis.coroutine.ViewModelCoroutineScope
import com.stonehiy.jetpackdemo.ApiSource
import com.stonehiy.jetpackdemo.entity.Author
import io.github.stonehiy.lib.core.CoreLiveData
import io.github.stonehiy.lib.core.coroutineJob


class ListViewModel : ViewModel() {


    val mChapters = CoreLiveData<List<Author>>()

//    val mutableLiveDataLoadInitial = MutableLiveData<PageKeyedDataSourceLoadInitial<Int, Author>>()
//    val mutableLiveDataLoadAfter = MutableLiveData<PageKeyedDataSourceLoadAfter<Int, Author>>()
//
//
//    val mediatorLiveData = MediatorLiveData<PagedListData<Int, Author>>()

//    val listPageKeyedDataSource = ListPageKeyedDataSource(this)

   val viewModelCoroutineScope = ViewModelCoroutineScope()


    fun getChapters() {
        coroutineJob({
            ApiSource.instance.getChapters()
        }, mChapters,viewModelCoroutineScope)


    }




}

