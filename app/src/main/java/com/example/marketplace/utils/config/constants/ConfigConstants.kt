package com.example.marketplace.utils.config.constants

import androidx.annotation.StringDef

class ConfigConstants {

    @StringDef(
        ConfigMainKeys.MAIN,
        ConfigMainKeys.VIEWS)

    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    annotation class ConfigMainKeys {
        companion object {
            const val MAIN = "main"
            const val VIEWS = "views"
        }
    }
}