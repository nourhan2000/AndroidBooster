package com.example.myapplication

import android.view.View

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}