package com.stonehiy.jetpackdemo.ui.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mModel = viewModelProvider(ViewModelProvider.AndroidViewModelFactory.getInstance(application))

        val binding: ActivityListBinding = DataBindingUtil.setContentView(this, R.layout.activity_list)

        val manager = LinearLayoutManager(this)
        val adapter = CustomAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = manager
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        pagedListLiveData.observe(this, Observer {
            Timber.i("it = $it")
            adapter.submitList(it)
        })


    }


    private val dataSourceList = object : PageKeyedDataSource<Int, Author>() {
        override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Author>) {
//            val list = listOf<Author>(Author(listOf<Any>(), 1, 1, "shigang", 1, 1, true, 1))
//            callback.onResult(list, 5, 6)
            runOnUiThread {
                mModel.getChapters()
                mModel.mChapters.observe(this@ListActivity, object : CoreObserver<List<Author>>(NetView(this@ListActivity)) {
                    override fun onSuccess(r: IResult<List<Author>>) {

                        // 这里的previousPageKey，和nextPageKey决定了前后是否有数据，如果你传个null，
                        // 那么就表示前边或者手边没有数据了。也就是下边的loadBefore或者LoadAfter不会执行了
                        callback.onResult(r.data(), 0, 1)

                    }

                })
            }

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
