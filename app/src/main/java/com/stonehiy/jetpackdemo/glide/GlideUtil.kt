package com.stonehiy.jetpackdemo.glide

import android.content.Context
import android.text.TextUtils
import android.widget.ImageView
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class GlideUtil {

    companion object {

        /**
         * 加载图片
         *
         * @param context  context
         * @param iv       imageView
         * @param url      图片地址
         * @param emptyImg 默认展位图
         */
        @JvmStatic
        fun loadImage(context: Context, iv: ImageView, url: String?, emptyImg: Int) {
            if (!TextUtils.isEmpty(url)) {
                //User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.109 Safari/537.36
                val glideUrl = GlideUrl(url, LazyHeaders.Builder()
                        //.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.109 Safari/537.36")
                        .build())
                GlideApp.with(context)
                        .load(glideUrl)
                        .error(emptyImg)
                        .placeholder(iv.drawable)
                        .transition(DrawableTransitionOptions().crossFade())
                        .into(iv)
            } else {
                loadImage(context, iv, emptyImg, emptyImg)
            }
        }

        /**
         * 加载圆角图片
         *
         * @param context  context
         * @param iv       imageView
         * @param url      图片地址
         * @param emptyImg 默认展位图
         */
        @JvmStatic
        fun loadRoundImage(context: Context, iv: ImageView, url: String, emptyImg: Int) {
            if (!TextUtils.isEmpty(url)) {
                GlideApp.with(context)
                        .load(url)
                        .error(emptyImg)
                        .placeholder(iv.drawable)
                        .transition(DrawableTransitionOptions().crossFade())
                        .transform(RoundedCorners(20)).into(iv)
            } else {
                loadRoundImage(context, iv, emptyImg, emptyImg)
            }
        }

        /**
         * 加载圆形图片
         *
         * @param context  context
         * @param iv       imageView
         * @param url      图片地址
         * @param emptyImg 默认展位图
         */
        @JvmStatic
        fun loadCircleImage(context: Context, iv: ImageView, url: String, emptyImg: Int) {
            if (!TextUtils.isEmpty(url)) {
                GlideApp.with(context)
                        .load(url)
                        .error(emptyImg)
                        .placeholder(iv.drawable)
                        .transition(DrawableTransitionOptions().crossFade())
                        .transform(CircleCrop()).into(iv)
            } else {
                loadCircleImage(context, iv, emptyImg, emptyImg)
            }
        }

        /**
         * 加载drawable中的图片资源
         *
         * @param context  context
         * @param iv       imageView
         * @param resId    图片地址
         * @param emptyImg 默认展位图
         */
        @JvmStatic
        fun loadImage(context: Context, iv: ImageView, resId: Int, emptyImg: Int) {
            GlideApp.with(context).load(resId).placeholder(emptyImg).into(iv)
        }

        /**
         * 加载drawable中的图片资源 圆角
         *
         * @param context  context
         * @param iv       imageView
         * @param resId    图片地址
         * @param emptyImg 默认展位图
         */
        @JvmStatic
        fun loadRoundImage(context: Context, iv: ImageView, resId: Int, emptyImg: Int) {
            GlideApp.with(context).load(emptyImg).placeholder(emptyImg).transform(RoundedCorners(20)).into(iv)
        }

        /**
         * 加载drawable中的图片资源 圆形
         *
         * @param context  context
         * @param iv       imageView
         * @param resId    图片地址
         * @param emptyImg 默认展位图
         */
        @JvmStatic
        fun loadCircleImage(context: Context, iv: ImageView, resId: Int, emptyImg: Int) {
            GlideApp.with(context).load(emptyImg).placeholder(emptyImg).transform(CircleCrop()).into(iv)
        }

    }
}