package com.stonehiy.jetpackdemo.entity

data class User(val admin: Boolean,
                val chapterTops: List<Any>,
                val collectIds: List<Int>,
                val email: String,
                val icon: String,
                val id: String,
                val nickname: String,
                var password: String,
                val token: String,
                val type: String,
                var username: String
)