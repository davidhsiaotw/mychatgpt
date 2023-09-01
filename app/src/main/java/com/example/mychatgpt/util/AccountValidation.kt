package com.example.mychatgpt.util

fun isPasswordValid(password: String): Boolean {
    return password.length >= 6
}