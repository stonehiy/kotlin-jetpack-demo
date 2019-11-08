package com.stonehiy.jetpackdemo.entity

data class PagedListData<K, V>(
        val list: List<V>,
        val pageKeyedDataSourceLoadInitial: PageKeyedDataSourceLoadInitial<K, V>?,
        val pageKeyedDataSourceLoadAfter: PageKeyedDataSourceLoadAfter<K, V>?
)