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
        mModel.pagedListLiveData.observe(this, Observer {
            adapter.submitList(it)
        })


        mModel.mutableLiveData.observe(this, Observer {
            mModel.mChapters.observe(this, object : CoreObserver<List<Author>>(NetView(this)) {
                override fun onSuccess(r: IResult<List<Author>>) {
                    it.callback.onResult(r.data(), null, 1)
                }

            })


        })


    }




}

