package com.stonehiy.jetpackdemo

import android.os.Bundle
import android.text.style.TtsSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.stonehiy.jetpackdemo.adapter.CustomAdapter
import com.stonehiy.jetpackdemo.base.NetView
import com.stonehiy.jetpackdemo.databinding.ActivityWeatherBinding
import com.stonehiy.jetpackdemo.entity.Author
import com.stonehiy.jetpackdemo.entity.Banner
import io.github.stonehiy.lib.core.CoreObserver
import io.github.stonehiy.lib.core.IResult
import io.github.stonehiy.lib.util.viewModelProvider
import timber.log.Timber

class WeatherActivity : AppCompatActivity() {


    val TAG = WeatherActivity::class.java.name

    private lateinit var mModel: WeatherViewModel
    var intMax = Int.MAX_VALUE




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_weather)

        mModel = viewModelProvider(ViewModelProvider.AndroidViewModelFactory.getInstance(application))

        var binding: ActivityWeatherBinding = DataBindingUtil.setContentView(this, R.layout.activity_weather)




        mModel.mBanners.observe(this, object : CoreObserver<List<Banner>>(NetView(this)) {
            override fun onSuccess(r: IResult<List<Banner>>) {
//                tvDemo.text = r.toString()
                val data = r.data()
                binding.banner = r.data()[0]
//                timer("timer", true, 2000, 2000) {
//                    val m = scheduledExecutionTime() / 1000
//                    Timber.i("mBanners timer $m")
//                    intMax--
//                    var i = intMax % data.size
//                    binding.banner = r.data()[i]
//                }
            }
        })

        mModel.mChapters.observe(this, object : CoreObserver<List<Author>>(NetView(this)) {
            override fun onSuccess(r: IResult<List<Author>>) {
                binding.author = r.data()[0]

            }
        })



        mModel.getBanners()
        mModel.getChapters()




    }


}





