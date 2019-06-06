package com.stonehiy.jetpackdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.stonehiy.jetpackdemo.entity.WeatherEntity

class WeatherActivity : AppCompatActivity() {
    val TAG = WeatherActivity::class.java.name

    private lateinit var mModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        mModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(WeatherViewModel::class.java)

        //创建观察者来更新UI
        val observer = Observer<WeatherEntity> { w ->
            Log.i(TAG, "w = $w ")

        }

        //观察livedata
        mModel.getWeather().observe(this, observer)

        mModel.launchDataLoad()


    }
}
