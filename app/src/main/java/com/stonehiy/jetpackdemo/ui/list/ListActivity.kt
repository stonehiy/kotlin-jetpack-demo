package com.stonehiy.jetpackdemo.ui.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stonehiy.jetpackdemo.R
import com.stonehiy.jetpackdemo.adapter.CustomAdapter
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

        val adapter = CustomAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        mModel.pagedListLiveData.observe(this, Observer {
            adapter.submitList(it)
        })

        /*
        mModel.mutableLiveDataLoadInitial.observe(this, Observer {
            mModel.mChapters.observe(this, object : CoreObserver<List<Author>>(NetView(this)) {
                override fun onSuccess(r: IResult<List<Author>>) {
                    it.callback.onResult(r.data(), null, 2)
                }

            })
        })
        mModel.mutableLiveDataLoadAfter.observe(this, Observer {
            mModel.mChapters.observe(this, object : CoreObserver<List<Author>>(NetView(this)) {
                override fun onSuccess(r: IResult<List<Author>>) {
                    it.callback.onResult(r.data(), it.params.key + 1)
                }

            })
        })
        */
        mModel.mediatorLiveData.addSource(mModel.mChapters, object : CoreObserver<List<Author>>(NetView(this)) {
            override fun onSuccess(r: IResult<List<Author>>) {
                mModel.mediatorLiveData.value = ListData(r.data(), mModel.mutableLiveDataLoadInitial.value, mModel.mutableLiveDataLoadAfter.value)

            }

        })


        mModel.mediatorLiveData.observe(this, Observer {
            if (null != it.mutableLiveDataLoadAfter) {
                it.mutableLiveDataLoadAfter?.callback?.onResult(it.coreLiveData, it.mutableLiveDataLoadAfter.params.key - 1)
                return@Observer
            }
            it.mutableLiveDataLoadInitial?.callback?.onResult(it.coreLiveData, null, 5)

        })


    }
}
