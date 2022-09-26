package com.example.marketplace.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun showSoftInput(context: Context) {
    val imm: InputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun hideSoftInput(context: Context) {
    val imm: InputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val isOpen = imm.isActive
    if (isOpen) imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
}

fun hideSoftInputFromWindow(context: Context, v: View) {
    val imm: InputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(v.windowToken, 0)
}