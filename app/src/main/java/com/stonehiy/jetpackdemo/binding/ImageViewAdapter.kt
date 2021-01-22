package com.stonehiy.jetpackdemo.binding

import android.widget.ImageView
import android.databinding.BindingAdapter
import com.stonehiy.jetpackdemo.glide.GlideUtil

object ImageViewAdapter {

    @JvmStatic
    @BindingAdapter("bind:imageUrl")
    fun loadImage(imageView: ImageView, url: String?) {
        GlideUtil.loadImage(imageView.context, imageView, url, 0)
    }


}