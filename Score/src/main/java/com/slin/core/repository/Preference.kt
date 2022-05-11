package com.slin.core.repository

import javax.inject.Qualifier

/**
 * Preference
 *
 * @author slin
 * @version 1.0.0
 * @since 2022/5/7
 */
interface Preference {

    fun <V> put(key: String, value: V)

    fun <V> get(key: String, defaultValue: V): V?


}


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SCorePreferenceQualifier