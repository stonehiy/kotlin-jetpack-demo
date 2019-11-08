package com.stonehiy.jetpackdemo.entity

import androidx.paging.PageKeyedDataSource

data class PageKeyedDataSourceLoadInitial<R, S>(val params: PageKeyedDataSource.LoadInitialParams<R>,
                                                val callback: PageKeyedDataSource.LoadInitialCallback<R, S>)
