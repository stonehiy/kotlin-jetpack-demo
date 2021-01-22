package com.stonehiy.jetpackdemo.ui.list


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager

import com.stonehiy.jetpackdemo.R

import com.stonehiy.jetpackdemo.base.NetView
import com.stonehiy.jetpackdemo.databinding.ActivityListBinding
import com.stonehiy.jetpackdemo.entity.Author
import io.github.stonehiy.lib.core.CoreObserver
import io.github.stonehiy.lib.core.IResult
import io.github.stonehiy.lib.util.viewModelProvider


class ListActivity : AppCompatActivity() {

    private lateinit var mModel: ListViewModel




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mModel = viewModelProvider(ViewModelProvider.AndroidViewModelFactory.getInstance(application))

        val binding: ActivityListBinding = DataBindingUtil.setContentView(this, R.layout.activity_list)
        binding.viewModel = mModel
        binding.activity = this
        binding.lifecycleOwner = this

//        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
//        mModel.pagedListLiveData.observe(this, Observer {
//            adapter.submitList(it)
//        })

        /*
        mModel.pageKeyedDataSourceLoadInitial.observe(this, Observer {
            mModel.mChapters.observe(this, object : CoreObserver<List<Author>>(NetView(this)) {
                override fun onSuccess(r: IResult<List<Author>>) {
                    it.callback.onResult(r.data(), null, 2)
                }

            })
        })
        mModel.pageKeyedDataSourceLoadAfter.observe(this, Observer {
            mModel.mChapters.observe(this, object : CoreObserver<List<Author>>(NetView(this)) {
                override fun onSuccess(r: IResult<List<Author>>) {
                    it.callback.onResult(r.data(), it.params.key + 1)
                }

            })
        })
        */
//        mModel.mediatorLiveData.addSource(mModel.mChapters, object : CoreObserver<List<Author>>(NetView(this)) {
//            override fun onSuccess(r: IResult<List<Author>>) {
//                mModel.mediatorLiveData.value = PagedListData(r.data(), mModel.mutableLiveDataLoadInitial.value, mModel.mutableLiveDataLoadAfter.value)
//
//            }
//
//        })


//        mModel.mediatorLiveData.observe(this, Observer {
//            if (null != it?.pageKeyedDataSourceLoadAfter) {
//                it.pageKeyedDataSourceLoadAfter?.callback?.onResult(it.list, it.pageKeyedDataSourceLoadAfter.params.key + 1)
//                return@Observer
//            }
//            it?.pageKeyedDataSourceLoadInitial?.callback?.onResult(it.list, null, 1)
//
//        })


    }


    fun refresh() {
//        adapter.submitList(null)
//        mModel.listPageKeyedDataSource.invalidate()
    }
}
