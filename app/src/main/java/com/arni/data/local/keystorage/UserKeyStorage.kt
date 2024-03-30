package com.arni.data.local.keystorage

import com.tencent.mmkv.MMKV

class UserKeyStorage(private val storage: MMKV) {

    fun saveToken(token: String) {
        storage.encode(USER_TOKEN, token)
    }

    fun saveLogin(login: String) {
        storage.encode(USER_LOGIN, login)
    }
    fun savePassword(pass: String) {
        storage.encode(USER_PASS, pass)
    }
    fun saveRole(role: String) {
        storage.encode(USER_ROLE, role)
    }

    fun getToken(): String? {
        return storage.decodeString(USER_TOKEN)
    }
    fun getLogin(): String? {
        return storage.decodeString(USER_LOGIN)
    }
    fun getPass(): String? {
        return storage.decodeString(USER_PASS)
    }
    fun getRole():String?{
        return storage.decodeString(USER_ROLE)
    }

    companion object {

        const val USER_TOKEN = "user_token"
        const val USER_ROLE = "user_role"
        const val USER_LOGIN = "user_login"
        const val USER_PASS = "user_pass"

    }
}
