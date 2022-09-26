package com.example.marketplace.utils.config

interface IAppConfigUpdateView {

    fun handleAppConfigResult()

    fun handleApkDownloadProgress(percent: Int)
}