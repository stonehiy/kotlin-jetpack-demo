package com.stonehiy.jetpackdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.stonehiy.jetpackdemo.databinding.ActivityMainBinding
import com.stonehiy.jetpackdemo.ui.list.ListActivity
import io.github.stonehiy.lib.util.viewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG = AppCompatActivity::class.java.name


    private lateinit var mModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mModel = viewModelProvider(ViewModelProvider.AndroidViewModelFactory.getInstance(application))
        var binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainActivity = this
        binding.viewModel = mModel


        //创建观察者来更新UI
        val observer = Observer<String> { value ->
            Log.d(TAG, "value = $value")
            tvValue.text = value

        }

        //观察livedata
        mModel.value.observe(this, observer)

//        btnChange.setOnClickListener {
        //            val v: String = "value is key"
//            mModel.value.value = v
//            startActivity(Intent(this, WeatherActivity::class.java))
//        }


    }


    fun click(view: View) {
        when (view.id) {
            R.id.btnChange -> {
                startActivity(Intent(this, WeatherActivity::class.java))
            }
            R.id.btnList -> {
                startActivity(Intent(this, ListActivity::class.java))
            }
        }

    }


}
