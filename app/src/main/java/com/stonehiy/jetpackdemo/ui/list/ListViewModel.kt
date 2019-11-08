package com.stonehiy.jetpackdemo.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stonehiy.jetpackdemo.ApiSource
import com.stonehiy.jetpackdemo.entity.Author
import com.stonehiy.jetpackdemo.entity.PageKeyedDataSourceLoadInitial
import io.github.stonehiy.lib.core.CoreLiveData
import io.github.stonehiy.lib.core.coroutineJob


class ListViewModel : ViewModel() {


    val mChapters = CoreLiveData<List<Author>>()

    val mutableLiveData = MutableLiveData<PageKeyedDataSourceLoadInitial<Int, Author>>().apply {
        getChapters()
    }


    fun getChapters() {
        coroutineJob({
            ApiSource.instance.getChapters()
        }, mChapters)
    }


}