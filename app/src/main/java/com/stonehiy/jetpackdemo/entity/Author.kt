package com.stonehiy.jetpackdemo.entity


data class Author(
        val children: List<Any>,
        val courseId: Int,
        val id: Int,
        val name: String,
        val order: Long,
        val parentChapterId: Int,
        val userControlSetTop: Boolean,
        val visible: Int

)