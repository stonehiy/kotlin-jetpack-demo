package com.stonehiy.jetpackdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.stonehiy.jetpackdemo.entity.Author
import io.github.stonehiy.lib.core.CoreActivity
import io.github.stonehiy.lib.entity.ResultEntity
import io.github.stonehiy.lib.result.MyResult
import io.github.stonehiy.lib.result.succeeded
import io.github.stonehiy.lib.result.successOr
import io.github.stonehiy.lib.util.ToastUtil
import kotlinx.android.synthetic.main.activity_weather.*
import timber.log.Timber

class WeatherActivity : CoreActivity() {
    val TAG = WeatherActivity::class.java.name

    private lateinit var mModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        mModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(WeatherViewModel::class.java)

        //创建观察者来更新UI
        val observer = Observer<MyResult<ResultEntity<List<Author>>>> {
            val successOr = it.successOr(null)
            if (it.succeeded) {
                tvDemo.text = successOr.toString()
            } else {

            }


        }
        //创建观察者来更新UI
        val observerError = Observer<String> { s ->
            ToastUtil.show(this, s, Toast.LENGTH_SHORT)

        }

        //观察livedata
        mModel.mMutableLiveData.observe(this, observer)
////        mModel.mMutableLiveDataError.observe(this, observerError)

        mModel.getData()


    }

    override fun onDestroy() {
        super.onDestroy()
    }


}
