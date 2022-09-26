package com.example.marketplace.utils

import android.annotation.SuppressLint
import android.widget.Toast

import android.content.Intent
import android.text.TextUtils
import android.app.Activity
import com.example.marketplace.R

@SuppressLint("QueryPermissionsNeeded")
fun shareLink(activity: Activity, url: String?) {
    if (TextUtils.isEmpty(url)) {
        Toast.makeText(activity, "cannot_share", Toast.LENGTH_SHORT).show()
        return
    }
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "text/plain"
    intent.putExtra(Intent.EXTRA_SUBJECT, activity.getString(R.string.app_name))
    intent.putExtra(Intent.EXTRA_TEXT, url)
    if (intent.resolveActivity(activity.packageManager) != null) {
        activity.startActivity(Intent.createChooser(intent, activity.getString(R.string.share_to)))
    } else {
        Toast.makeText(activity, "cannot_share", Toast.LENGTH_SHORT).show()
    }
}