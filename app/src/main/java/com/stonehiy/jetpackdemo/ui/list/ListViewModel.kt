package com.stonehiy.jetpackdemo.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
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


    private val factory = object : DataSource.Factory<Int, Author>() {
        override fun create(): DataSource<Int, Author> {
            return ListPageKeyedDataSource(this@ListViewModel)
        }

    }

    val pagedListLiveData = LivePagedListBuilder(factory, PagedList.Config.Builder()
            .setPageSize(1)
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(1)
            .build()).build()


    fun getChapters() {
        coroutineJob({
            ApiSource.instance.getChapters()
        }, mChapters)
    }


}