package com.stonehiy.jetpackdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG = AppCompatActivity::class.java.name


    private lateinit var mModel: DemoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(DemoViewModel::class.java)


        //创建观察者来更新UI
        val observer = Observer<String> { value ->
            Log.d(TAG, "value = $value")
            tvValue.setText(value)

        }

        //观察livedata
        mModel.value.observe(this, observer)

        btnChange.setOnClickListener {
            //            val v: String = "value is key"
//            mModel.value.value = v
            startActivity(Intent(this, WeatherActivity::class.java))
        }


    }


}
