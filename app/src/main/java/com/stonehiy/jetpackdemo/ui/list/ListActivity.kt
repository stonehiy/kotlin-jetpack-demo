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
import com.stonehiy.jetpackdemo.R
import com.stonehiy.jetpackdemo.WeatherViewModel
import com.stonehiy.jetpackdemo.adapter.CustomAdapter
import com.stonehiy.jetpackdemo.base.NetView
import com.stonehiy.jetpackdemo.databinding.ActivityListBinding
import com.stonehiy.jetpackdemo.databinding.ActivityWeatherBinding
import com.stonehiy.jetpackdemo.entity.Author
import io.github.stonehiy.lib.core.CoreObserver
import io.github.stonehiy.lib.core.IResult
import io.github.stonehiy.lib.util.viewModelProvider
import timber.log.Timber

class ListActivity : AppCompatActivity() {

    private lateinit var mModel: ListViewModel


    private val dataSource = object : PageKeyedDataSource<Int, Author>() {
        override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Author>) {
            Timber.i("PageKeyedDataSource loadInitial params = $params   callback = $callback")
            runOnUiThread {
                mModel.getChapters()
                mModel.mChapters.observe(this@ListActivity, object : CoreObserver<List<Author>>(NetView(this@ListActivity)) {
                    override fun onSuccess(r: IResult<List<Author>>) {
                        Timber.i("PageKeyedDataSource loadInitial observe = ${r.data()}")
                        callback.onResult(r.data(), 1, 2)
                    }
                })
            }

        }

        override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Author>) {
            Timber.i("PageKeyedDataSource loadAfter params = $params   callback = $callback")
        }

        override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Author>) {
            Timber.i("PageKeyedDataSource loadBefore params = $params   callback = $callback")
        }


    }
    private val factory = object : DataSource.Factory<Int, Author>() {
        override fun create(): DataSource<Int, Author> {
            return dataSource
        }

    }

    private val pagedListLiveData = LivePagedListBuilder(factory, PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(20)
            .build()).build()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mModel = viewModelProvider(ViewModelProvider.AndroidViewModelFactory.getInstance(application))

        var binding: ActivityListBinding = DataBindingUtil.setContentView(this, R.layout.activity_list)

        val adapter = CustomAdapter()
        binding.recyclerView.adapter = adapter

        pagedListLiveData.observe(this, Observer {
            Timber.i("it = $it")
            adapter.submitList(it)
        })
    }
}
