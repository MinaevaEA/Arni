package com.arni.data.local.keystorage

import com.tencent.mmkv.MMKV

class UserKeyStorage(private val storage: MMKV) {

    fun saveToken(token: String) {
        storage.encode(USER_TOKEN, token)
    }

    fun saveRole(role: String) {
        storage.encode(USER_ROLE, role)
    }

    fun getToken(): String? {
        return storage.decodeString(USER_TOKEN)
    }

    fun getRole(): String? {
        return storage.decodeString(USER_ROLE)
    }

    companion object {

        const val USER_TOKEN = "user_token"
        const val USER_ROLE = "user_role"
    }
}
