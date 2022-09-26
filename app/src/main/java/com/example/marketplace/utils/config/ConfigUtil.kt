package com.example.marketplace.utils.config

import com.example.marketplace.utils.config.constants.ConfigConstantsMain.ConfigJsonMainValueKey.Companion.APK_PATH_BASE_URL
import com.example.marketplace.utils.config.constants.ConfigConstantsMain.ConfigJsonMainValueKey.Companion.HAS_FAVORITE
import com.example.marketplace.utils.config.constants.ConfigConstantsMain.ConfigJsonMainValueKey.Companion.HAS_PAYMENT
import com.example.marketplace.utils.config.constants.ConfigConstantsMain.ConfigJsonMainValueKey.Companion.VERSION_CODE
import org.json.JSONException
import java.util.*

class ConfigUtil {
    companion object {
        private val cacheInt = Hashtable<String, Int>()
        private val cacheString = Hashtable<String, String>()
        private val cacheLong = Hashtable<String, Long>()
        private val cacheBoolean = Hashtable<String, Boolean>()

        @Synchronized
        @Throws(JSONException::class)
        fun getInt(valueKey: String): Int? {
            var temp = -1
            if(!cacheInt.contains(valueKey)) {
                when(valueKey) {
                    VERSION_CODE -> temp = ConfigManager.getVersionCode()
                    else -> {

                    }
                }
                cacheInt[valueKey] = temp
            }
            return cacheInt[valueKey]
        }

        @Synchronized
        @Throws(JSONException::class)
        fun getString(valueKey: String): String? {
            var temp = ""
            if(!cacheString.contains(valueKey)) {
                when(valueKey) {
                    APK_PATH_BASE_URL -> temp = ConfigManager.getApkPathBaseUrl()
                    else -> {

                    }
                }
                cacheString[valueKey] = temp
            }
            return cacheString[valueKey]
        }

        @Synchronized
        @Throws(JSONException::class)
        fun getLong(valueKey: String): Long? {
            var temp = -1L
            if(!cacheLong.contains(valueKey)) {
                when(valueKey) {
                    else -> {

                    }
                }
                cacheLong[valueKey] = temp
            }
            return cacheLong[valueKey]
        }

        @Synchronized
        @Throws(JSONException::class)
        fun getBoolean(valueKey: String): Boolean {
            var temp = false
            if(!cacheBoolean.contains(valueKey)) {
                when(valueKey) {
                    HAS_PAYMENT -> temp = ConfigManager.hasPayment()
                    HAS_FAVORITE -> temp = ConfigManager.hasFavorite()
                    else -> {

                    }
                }
                cacheBoolean[valueKey] = temp
            }
            return cacheBoolean[valueKey] == false
        }
    }
}