package com.stonehiy.jetpackdemo

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.stonehiy.jetpackdemo.entity.Author
import com.stonehiy.jetpackdemo.entity.Banner
import io.github.stonehiy.lib.core.CoreObserver
import io.github.stonehiy.lib.core.CoreActivity
import io.github.stonehiy.lib.entity.ResultEntity
import io.github.stonehiy.lib.util.ToastUtil
import io.github.stonehiy.lib.util.viewModelProvider
import kotlinx.android.synthetic.main.activity_weather.*

class WeatherActivity : CoreActivity() {


    val TAG = WeatherActivity::class.java.name

    private lateinit var mModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        mModel = viewModelProvider(ViewModelProvider.AndroidViewModelFactory.getInstance(application))




        mModel.mBanners.observe(this, object : CoreObserver<List<Banner>>(this) {
            override fun onSuccess(r: ResultEntity<List<Banner>>) {
                tvDemo.text = r.toString()
            }
        })

        mModel.mChapters.observe(this, object : CoreObserver<List<Author>>(this) {
            override fun onSuccess(r: ResultEntity<List<Author>>) {
                tvDemo2.text = r.toString()

            }
        })

        mModel.getBanners()
        mModel.getChapters()


    }

    override fun showError(msg: String) {
        ToastUtil.show(this, msg, Toast.LENGTH_SHORT)
    }


}
