package com.app.bank_misr.data.local_storage.prefs

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV
import androidx.security.crypto.EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
import androidx.security.crypto.MasterKeys
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PrefStoreImpl @Inject constructor(
  @ApplicationContext val context: Context
) : PrefStore {

  private val masterKey = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
  private var prefs = EncryptedSharedPreferences.create(
    "${context.packageName}_preferences",
    masterKey,
    context,
    AES256_SIV,
    AES256_GCM
  )

  override fun getUserToken() = get(PreferencesKeys.USER_TOKEN, "")

  override fun setUserToken(value: String) = put(PreferencesKeys.USER_TOKEN, value)

  override fun isUser(): Boolean {
    return !getUserToken().isNullOrEmpty()
  }

  override fun remove(vararg keys: String) {
    for (key in keys) {
      prefs.edit().remove(key).apply()
    }
  }

  override fun clear() = prefs.edit().clear().apply()

  private fun put(key: String, value: Any) {
    when (value) {
      is Int -> prefs.edit().putInt(key, value).apply()
      is Long -> prefs.edit().putLong(key, value).apply()
      is Float -> prefs.edit().putFloat(key, value).apply()
      is String -> prefs.edit().putString(key, value).apply()
      is Boolean -> prefs.edit().putBoolean(key, value).apply()
    }
  }

  @Suppress("UNCHECKED_CAST")
  private fun <T> get(key: String, defaultValue: T): T {
    return when (defaultValue) {
      is Int -> prefs.getInt(key, defaultValue)
      is Long -> prefs.getLong(key, defaultValue)
      is Float -> prefs.getFloat(key, defaultValue)
      is String -> prefs.getString(key, defaultValue) ?: defaultValue
      is Boolean -> prefs.getBoolean(key, defaultValue)
      else -> return defaultValue
    } as T
  }
}

object PreferencesKeys {

  const val USER_TOKEN = "FcmToken"
}