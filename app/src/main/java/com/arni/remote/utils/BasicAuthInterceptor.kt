package com.arni.remote.utils

import com.arni.data.local.keystorage.UserKeyStorage
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class BasicAuthInterceptor(val userKeyStorage: UserKeyStorage): Interceptor {
    private var credentials: String = Credentials.basic(userKeyStorage.getLogin() ?: "", userKeyStorage.getPass() ?: "")


    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder().header("Authorization", credentials).build()

        return chain.proceed(request)
    }
}
