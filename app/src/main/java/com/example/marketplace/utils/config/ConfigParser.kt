package com.example.marketplace.utils.config

import android.text.TextUtils
import com.example.marketplace.utils.config.constants.ConfigConstants.ConfigMainKeys.Companion.VIEWS
import com.google.gson.JsonObject
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class ConfigParser {

    companion object {
        @JvmField
        val ourInstance: ConfigParser = ConfigParser()
        val viewsMap: MutableMap<String, JSONObject?> = mutableMapOf()
    }

    private lateinit var response: JsonObject

    fun setResponse(response: JsonObject) {
        this.response = response
        parse()
    }

    fun getJsonStringValueByKey(jsonObject: JsonObject?, jsonKey: String): String? {
        return jsonObject?.get(jsonKey)?.asString
    }

    fun getJsonIntValueByKey(jsonObject: JsonObject?, jsonKey: String): Int? {
        return jsonObject?.get(jsonKey)?.asInt
    }

    fun getJsonLongValueByKey(jsonObject: JsonObject?, jsonKey: String): Long? {
        return jsonObject?.get(jsonKey)?.asLong
    }

    fun getJsonBooleanValueByKey(jsonObject: JsonObject?, jsonKey: String): Boolean? {
        return jsonObject?.get(jsonKey)?.asBoolean
    }

    private fun parse() {
        var respObj: JSONObject? = null
        try {
            respObj = JSONObject(response.toString())
        } catch (var11: JSONException) {
            var11.printStackTrace()
        }
        val viewsArray: JSONArray? = respObj?.optJSONArray(VIEWS)
        viewsArray?.let { jsonArray ->
            for (i in 0 until jsonArray.length()) {
                var issueObj: JSONObject? = null
                try {
                    issueObj = jsonArray[i] as JSONObject
                } catch (var10: JSONException) {
                    var10.printStackTrace()
                }
                issueObj?.let { jsonObject ->
                    val iterator: Iterator<*> = jsonObject.keys()
                    while (iterator.hasNext()) {
                        val key = iterator.next() as String
                        var issue: JSONObject? = null
                        try {
                            issue = jsonObject.getJSONObject(key)
                        } catch (var9: JSONException) {
                            var9.printStackTrace()
                        }
                        issue?.let {
                            viewsMap.put(key, it)
                        }
                    }
                }
            }
        }
    }

    fun getJsonObjectByKey(jsonObjKey: String): JsonObject? {
        return if (TextUtils.isEmpty(response.toString())) {
            null
        } else {
            var respObj: JsonObject? = null
            try {
                respObj = response
            } catch (var4: JSONException) {
                var4.printStackTrace()
            }
            respObj?.getAsJsonObject(jsonObjKey)
        }
    }

    fun getViewJsonByKey(key: String): JSONObject {
        return viewsMap[key] as JSONObject
    }
}