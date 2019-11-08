package com.stonehiy.jetpackdemo.ui.list

import androidx.paging.PageKeyedDataSource
import com.stonehiy.jetpackdemo.entity.Author
import com.stonehiy.jetpackdemo.entity.PageKeyedDataSourceLoadAfter
import com.stonehiy.jetpackdemo.entity.PageKeyedDataSourceLoadInitial
import timber.log.Timber

class ListPageKeyedDataSource(val viewModel: ListViewModel) : PageKeyedDataSource<Int, Author>() {

    override fun loadInitial(params: PageKeyedDataSource.LoadInitialParams<Int>, callback: PageKeyedDataSource.LoadInitialCallback<Int, Author>) {
        Timber.i("loadInitial params = $params")
        viewModel.getChapters()
        viewModel.mutableLiveDataLoadInitial.postValue(PageKeyedDataSourceLoadInitial(params, callback))
    }

    override fun loadAfter(params: PageKeyedDataSource.LoadParams<Int>, callback: PageKeyedDataSource.LoadCallback<Int, Author>) {
        Timber.i("loadAfter params = ${params.key}")
        viewModel.getChapters()
        viewModel.mutableLiveDataLoadAfter.postValue(PageKeyedDataSourceLoadAfter(params, callback))
    }

    override fun loadBefore(params: PageKeyedDataSource.LoadParams<Int>, callback: PageKeyedDataSource.LoadCallback<Int, Author>) {
        Timber.i("loadBefore params = ${params.key}")
    }

}