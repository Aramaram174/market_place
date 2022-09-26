package com.example.marketplace.utils

import com.google.gson.Gson
import com.google.gson.JsonObject
import org.json.JSONArray
import org.json.JSONObject

class GsonParserUtils {
    companion object {
        fun <T> parse(jsonObject: JsonObject, cls: Class<T>): T {
            return Gson().fromJson(jsonObject.toString(), cls)
        }

        fun <T> parse(jsonObject: JSONObject, cls: Class<T>): T {
            return Gson().fromJson(jsonObject.toString(), cls)
        }

        fun <T> parse(jsonArray: JSONArray, cls: Class<Array<T>?>): Array<T>? {
            return Gson().fromJson(jsonArray.toString(), cls)
        }
    }
}