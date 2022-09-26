package com.example.marketplace.utils

import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.PackageInfo
import com.example.marketplace.R

private fun getVersionName(context: Context): String {
    return try {
        val pi: PackageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        pi.versionName
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
        context.getString(R.string.version_unknown)
    }
}

private fun getVersionCode(context: Context): Int {
    return try {
        val pi: PackageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        pi.versionCode
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
        0
    }
}

fun getVersion(context: Context): String? {
    val versionName = getVersionName(context)
    val versionCode = getVersionCode(context)
    return ""
//    return context.getString(
//        R.string.version_name_format,
//        versionName,
//        versionCode
//    )
}