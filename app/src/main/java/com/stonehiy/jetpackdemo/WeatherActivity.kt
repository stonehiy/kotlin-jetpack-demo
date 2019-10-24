package com.stonehiy.jetpackdemo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.stonehiy.jetpackdemo.base.NetView
import com.stonehiy.jetpackdemo.databinding.ActivityWeatherBinding
import com.stonehiy.jetpackdemo.entity.Author
import com.stonehiy.jetpackdemo.entity.Banner
import io.github.stonehiy.lib.core.CoreObserver
import io.github.stonehiy.lib.core.CoreActivity
import io.github.stonehiy.lib.core.IResult
import io.github.stonehiy.lib.util.ToastUtil
import io.github.stonehiy.lib.util.viewModelProvider
import kotlinx.android.synthetic.main.activity_weather.*

class WeatherActivity : AppCompatActivity() {


    val TAG = WeatherActivity::class.java.name

    private lateinit var mModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_weather)

        mModel = viewModelProvider(ViewModelProvider.AndroidViewModelFactory.getInstance(application))

        var binding: ActivityWeatherBinding = DataBindingUtil.setContentView(this, R.layout.activity_weather)




        mModel.mBanners.observe(this, object : CoreObserver<List<Banner>>(NetView(this)) {
            override fun onSuccess(r: IResult<List<Banner>>) {
//                tvDemo.text = r.toString()
                binding.banner = r.data()[0]
            }
        })

        mModel.mChapters.observe(this, object : CoreObserver<List<Author>>(NetView(this)) {
            override fun onSuccess(r: IResult<List<Author>>) {
//                tvDemo2.text = r.toString()
                binding.author = r.data()[0]

            }
        })

        mModel.getBanners()
        mModel.getChapters()


    }


}
