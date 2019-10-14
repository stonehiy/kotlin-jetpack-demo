package io.github.stonehiy.lib.core

import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import io.github.stonehiy.lib.R
import io.github.stonehiy.lib.entity.ResultEntity
import io.github.stonehiy.lib.result.MyResult


open class CoreLiveData<R> : MutableLiveData<MyResult<ResultEntity<R>>>() {


}