package com.stonehiy.jetpackdemo.ui.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.stonehiy.jetpackdemo.R
import com.stonehiy.jetpackdemo.adapter.CustomAdapter
import com.stonehiy.jetpackdemo.base.NetView
import com.stonehiy.jetpackdemo.databinding.ActivityListBinding
import com.stonehiy.jetpackdemo.entity.Author
import com.stonehiy.jetpackdemo.entity.PageKeyedDataSourceLoadInitial
import io.github.stonehiy.lib.core.CoreObserver
import io.github.stonehiy.lib.core.IResult
import io.github.stonehiy.lib.util.viewModelProvider
import timber.log.Timber

class ListActivity : AppCompatActivity() {

    private lateinit var mModel: ListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mModel = viewModelProvider(ViewModelProvider.AndroidViewModelFactory.getInstance(application))

        val binding: ActivityListBinding = DataBindingUtil.setContentView(this, R.layout.activity_list)

        val adapter = CustomAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        pagedListLiveData.observe(this, Observer {
            Timber.i("it = $it")
            adapter.submitList(it)
        })


        mModel.mutableLiveData.observe(this, Observer {
            mModel.mChapters.observe(this, object : CoreObserver<List<Author>>(NetView(this)) {
                override fun onSuccess(r: IResult<List<Author>>) {
                    it.callback.onResult(r.data(), 0, 1)
                }

            })


        })


    }


    private val dataSourceList = object : PageKeyedDataSource<Int, Author>() {
        override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Author>) {
            mModel.mutableLiveData.postValue(PageKeyedDataSourceLoadInitial<Int, Author>(params, callback))
        }

        override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Author>) {
            Timber.i("loadAfter = ${params.key}")
        }

        override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Author>) {
            Timber.i("loadBefore = ${params.key}")
        }

    }


    private val factory = object : DataSource.Factory<Int, Author>() {
        override fun create(): DataSource<Int, Author> {
            return dataSourceList
        }

    }

    private val pagedListLiveData = LivePagedListBuilder(factory, PagedList.Config.Builder()
            .setPageSize(1)
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(1)
            .build()).build()
}

