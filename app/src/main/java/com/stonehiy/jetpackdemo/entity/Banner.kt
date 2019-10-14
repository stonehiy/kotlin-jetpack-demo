package com.stonehiy.jetpackdemo.entity


data class Banner(
        val desc: String,
        val imagePath: String,
        val id: Int,
        val isVisible: Int,
        val order: Long,
        val title: String,
        val url: String,
        val type: Int

)