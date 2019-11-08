package com.stonehiy.jetpackdemo.ui.list

import androidx.paging.PageKeyedDataSource
import com.stonehiy.jetpackdemo.entity.Author
import com.stonehiy.jetpackdemo.entity.PageKeyedDataSourceLoadInitial
import timber.log.Timber

class ListPageKeyedDataSource(val viewModel: ListViewModel) : PageKeyedDataSource<Int, Author>() {

    override fun loadInitial(params: PageKeyedDataSource.LoadInitialParams<Int>, callback: PageKeyedDataSource.LoadInitialCallback<Int, Author>) {
        viewModel.mutableLiveData.postValue(PageKeyedDataSourceLoadInitial<Int, Author>(params, callback))
    }

    override fun loadAfter(params: PageKeyedDataSource.LoadParams<Int>, callback: PageKeyedDataSource.LoadCallback<Int, Author>) {
        Timber.i("loadAfter = ${params.key}")
    }

    override fun loadBefore(params: PageKeyedDataSource.LoadParams<Int>, callback: PageKeyedDataSource.LoadCallback<Int, Author>) {
        Timber.i("loadBefore = ${params.key}")
    }

}