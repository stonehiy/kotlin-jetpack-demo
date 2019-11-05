package com.stonehiy.jetpackdemo.ui.list

import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.stonehiy.jetpackdemo.ApiSource
import com.stonehiy.jetpackdemo.entity.Author
import com.stonehiy.jetpackdemo.entity.Banner
import io.github.stonehiy.lib.core.CoreLiveData
import io.github.stonehiy.lib.core.CoreViewModel
import io.github.stonehiy.lib.core.coroutineJob
import timber.log.Timber


class ListViewModel : ViewModel() {


    val mChapters = CoreLiveData<List<Author>>()



    fun getChapters() {
        coroutineJob({
            ApiSource.instance.getChapters()
        }, mChapters)

    }


}