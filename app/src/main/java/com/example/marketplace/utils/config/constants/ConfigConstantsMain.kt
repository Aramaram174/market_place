package com.example.marketplace.utils.config.constants

import androidx.annotation.StringDef

class ConfigConstantsMain {

    @StringDef(
        ConfigJsonMainValueKey.VERSION_CODE,
        ConfigJsonMainValueKey.SITE_NAME,
        ConfigJsonMainValueKey.SITE_ID,
        ConfigJsonMainValueKey.HOST_NAME,
        ConfigJsonMainValueKey.CMS_HOST,
        ConfigJsonMainValueKey.SOURCE
    )

    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    annotation class ConfigJsonMainValueKey {
        companion object {
            const val VERSION_CODE = "version_code"
            const val SITE_ID = "site_id"
            const val SITE_NAME = "site_name"
            const val HOST_NAME = "host_name"
            const val CMS_HOST = "cms_host"
            const val SOURCE = "source"
            const val APK_PATH_BASE_URL = "apk_path_base_url"
            const val HAS_PAYMENT = "has_payments"
            const val HAS_FAVORITE = "has_favorite"
        }
    }
}