package com.stonehiy.jetpackdemo.entity

import androidx.paging.PageKeyedDataSource

data class PageKeyedDataSourceLoadAfter<R, S>(val params: PageKeyedDataSource.LoadParams<R>,
                                              val callback: PageKeyedDataSource.LoadCallback<R, S>)
