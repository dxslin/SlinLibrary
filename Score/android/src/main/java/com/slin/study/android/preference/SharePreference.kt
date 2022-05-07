package com.slin.study.android.preference

import android.content.SharedPreferences
import com.slin.core.repository.Preference

/**
 * SharePreference
 *
 * @author slin
 * @version 1.0.0
 * @since 2022/5/7
 */
class SharePreference(private val preferences: SharedPreferences) : Preference,
    SharedPreferences by preferences {

    override fun <V> put(key: String, value: V) {
        preferences.edit().apply {
            when (value) {
                is Int -> putInt(key, value)
                is Long -> putLong(key, value)
                is Float -> putFloat(key, value)
                is Boolean -> putBoolean(key, value)
                is String -> putString(key, value)
                is Set<*> -> {
                    try {
                        @Suppress("UNCHECKED_CAST")
                        putStringSet(key, value as Set<String>)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        throw IllegalArgumentException("Unsupported value type: ${value!!::class.java}")
                    }
                }
                else -> throw IllegalArgumentException("Unsupported value type: ${value!!::class.java}")
            }

        }.apply()
    }

    @Suppress("UNCHECKED_CAST", "IMPLICIT_CAST_TO_ANY")
    override fun <V> get(key: String, defaultValue: V): V {
        return when (defaultValue) {
            is Int -> preferences.getInt(key, defaultValue)
            is Long -> preferences.getLong(key, defaultValue)
            is Float -> preferences.getFloat(key, defaultValue)
            is Boolean -> preferences.getBoolean(key, defaultValue)
            is String -> preferences.getString(key, defaultValue)
            is Set<*> -> preferences.getStringSet(key, defaultValue as Set<String>)
            else -> throw IllegalArgumentException("Unsupported default type: ${defaultValue!!::class.java}")
        } as V
    }

}