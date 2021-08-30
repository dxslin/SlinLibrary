package com.slin.core.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


/**
 * author: slin
 * date: 2020/9/16
 * description: json 转换
 *
 */
object GsonUtils {

    val INSTANCE: Gson = Gson()
}

fun <T> T.toJson(): String {
    return GsonUtils.INSTANCE.toJson(this)
}

inline fun <reified T> String.fromJson(): T {
    return GsonUtils.INSTANCE.fromJson(this, T::class.java)
}

inline fun <reified T> String.fromJsonArray(): List<T> {
    return GsonUtils.INSTANCE.fromJson(this, object : TypeToken<List<T>>() {}.type)
}