package com.example.marketplace.utils.config.constants

import androidx.annotation.StringDef

class ConfigConstantsView {

    @StringDef(
        ConfigJsonViewsValueKey.BOTTOM_NAVIGATION_VIEW
    )

    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    @ConfigConstants.ConfigMainKeys
    annotation class ConfigJsonViewsValueKey {
        companion object {
            const val BOTTOM_NAVIGATION_VIEW = "bottom_navigation_view"
        }
    }
}