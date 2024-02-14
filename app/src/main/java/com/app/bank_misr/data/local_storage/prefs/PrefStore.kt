package com.app.bank_misr.data.local_storage.prefs

interface PrefStore {

  fun getUserToken(): String?

  fun setUserToken(value: String)

  fun isUser(): Boolean

  fun remove(vararg keys: String)

  fun clear()
}