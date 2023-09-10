package com.example.mychatgpt.util

import android.util.Log

fun debug(tag: String, msg: String) {
    Log.d(tag, msg)
}

fun error(tag: String, msg: String) {
    Log.e(tag, msg)
}