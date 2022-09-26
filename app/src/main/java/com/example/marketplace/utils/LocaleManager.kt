package com.example.marketplace.utils

import android.content.Context

import android.preference.PreferenceManager

import android.content.res.Configuration
import android.content.res.Resources
import java.util.*

class LocaleManager {

    companion object {
        private const val LANGUAGE_KEY = "language_key"

        fun setLocale(mContext: Context): Context {
            val savedLanguage = getLanguagePref(mContext)
            return savedLanguage?.let { updateResources(mContext, it) } ?: mContext
        }

        fun setNewLocale(mContext: Context, language: String): Context {
            setLanguagePref(mContext, language)
            return updateResources(mContext, language)
        }

        fun getLanguagePref(mContext: Context): String? {
            val mPreferences = PreferenceManager.getDefaultSharedPreferences(mContext)
            val currentLocale = getLocale(mContext.resources)
            return if (!mPreferences.contains(LANGUAGE_KEY)) null else mPreferences.getString(LANGUAGE_KEY, currentLocale.toString())
        }

        private fun setLanguagePref(mContext: Context, localeKey: String) {
            val mPreferences = PreferenceManager.getDefaultSharedPreferences(mContext)
            mPreferences.edit().putString(LANGUAGE_KEY, localeKey).apply()
        }

        private fun updateResources(context: Context, language: String): Context {
            var context: Context = context
            val locale = Locale(language)
            Locale.setDefault(locale)
            val res: Resources = context.resources
            val config = Configuration(res.configuration)
            config.setLocale(locale)
            context = context.createConfigurationContext(config)
            return context
        }

        fun getLocale(res: Resources): Locale {
            val config = res.configuration
            return config.locales.get(0)
        }

        fun getSavedLocale(context: Context): Locale {
            val savedLanguage = getLanguagePref(context)
            return if (savedLanguage == null) getLocale(context.resources) else Locale(getLanguagePref(context))
        }
    }
}