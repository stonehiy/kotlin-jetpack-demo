package com.stonehiy.jetpackdemo

import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.stonehiy.jetpackdemo.base.CoreActivity
import com.stonehiy.jetpackdemo.databinding.ActivityWeatherBinding
import io.github.stonehiy.lib.util.viewModelProvider

class WeatherActivity : CoreActivity<WeatherViewModel, ActivityWeatherBinding>() {


    private lateinit var mModel: WeatherViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_weather)

        mModel = viewModelProvider(ViewModelProvider.AndroidViewModelFactory.getInstance(application))

        var binding: ActivityWeatherBinding = DataBindingUtil.setContentView(this, R.layout.activity_weather)










    }

    override fun layoutId(): Int = R.layout.activity_weather

    override fun initView(savedInstanceState: Bundle?) {

    }


}





