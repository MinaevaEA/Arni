package com.arni.remote.utils

import okhttp3.Interceptor
import okhttp3.Response

class HeadersInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder()
            .also {
                it.header("Accept", "application/json")
            }
            .build()

        return chain.proceed(request)
    }

}
