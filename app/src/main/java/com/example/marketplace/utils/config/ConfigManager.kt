package com.example.marketplace.utils.config

import com.example.marketplace.data.db.model.BottomNavigationView
import com.example.marketplace.utils.GsonParserUtils
import com.example.marketplace.utils.config.constants.ConfigConstants.ConfigMainKeys.Companion.MAIN
import com.example.marketplace.utils.config.constants.ConfigConstantsMain.ConfigJsonMainValueKey.Companion.APK_PATH_BASE_URL
import com.example.marketplace.utils.config.constants.ConfigConstantsMain.ConfigJsonMainValueKey.Companion.VERSION_CODE
import com.example.marketplace.utils.config.constants.ConfigConstantsMain.ConfigJsonMainValueKey.Companion.SITE_ID
import com.example.marketplace.utils.config.constants.ConfigConstantsMain.ConfigJsonMainValueKey.Companion.SOURCE
import com.example.marketplace.utils.config.constants.ConfigConstantsMain.ConfigJsonMainValueKey.Companion.HAS_FAVORITE
import com.example.marketplace.utils.config.constants.ConfigConstantsMain.ConfigJsonMainValueKey.Companion.HAS_PAYMENT
import com.example.marketplace.utils.config.constants.ConfigConstantsView.ConfigJsonViewsValueKey.Companion.BOTTOM_NAVIGATION_VIEW
import org.json.JSONException

class ConfigManager {
    companion object {
        @Throws(JSONException::class)
        fun getVersionCode(): Int = ConfigParser.ourInstance.getJsonIntValueByKey(ConfigParser.ourInstance.getJsonObjectByKey(MAIN), VERSION_CODE) ?: -1

        @Throws(JSONException::class)
        fun getSiteId(): Int = ConfigParser.ourInstance.getJsonIntValueByKey(ConfigParser.ourInstance.getJsonObjectByKey(MAIN), SITE_ID) ?: -1

        @Throws(JSONException::class)
        fun getApkPathBaseUrl(): String = ConfigParser.ourInstance.getJsonStringValueByKey(ConfigParser.ourInstance.getJsonObjectByKey(MAIN), APK_PATH_BASE_URL) ?: ""

        @Throws(JSONException::class)
        fun getSource(): Int = ConfigParser.ourInstance.getJsonIntValueByKey(ConfigParser.ourInstance.getJsonObjectByKey(MAIN), SOURCE) ?: -1

        @Throws(JSONException::class)
        fun hasPayment(): Boolean = ConfigParser.ourInstance.getJsonBooleanValueByKey(ConfigParser.ourInstance.getJsonObjectByKey(MAIN), HAS_PAYMENT) ?: false

        @Throws(JSONException::class)
        fun hasFavorite(): Boolean = ConfigParser.ourInstance.getJsonBooleanValueByKey(ConfigParser.ourInstance.getJsonObjectByKey(MAIN), HAS_FAVORITE) ?: false

        @Throws(JSONException::class)
        fun getBottomNavigationView(): BottomNavigationView {
            return GsonParserUtils.parse(ConfigParser.ourInstance.getViewJsonByKey(BOTTOM_NAVIGATION_VIEW), BottomNavigationView::class.java)
        }
    }
}