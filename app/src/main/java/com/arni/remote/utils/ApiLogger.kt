package com.arni.remote.utils

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

class ApiLogger : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        if (message.startsWith("{") || message.startsWith("[")) {
            try {
                val prettyPrintJson = GsonBuilder()
                    .serializeNulls()
                    .setPrettyPrinting()
                    .create()
                    .toJson(JsonParser.parseString(message))
                Timber.d(prettyPrintJson)
            } catch (m: JsonSyntaxException) {
                Timber.d(message)
            }
        } else {
            Timber.d(message)
            return
        }
    }
}
