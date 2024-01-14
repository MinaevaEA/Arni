package com.arni.data.di

import com.arni.remote.Api
import com.arni.remote.utils.ApiLogger
import com.arni.remote.utils.HeadersInterceptor
import com.arni.remote.utils.NetworkConnectionInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import com.arni.remote.utils.TokenInterceptor
import com.tencent.mmkv.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val remoteStorageModule = module {

    single {
        OkHttpClient.Builder()
            .connectTimeout(40, TimeUnit.SECONDS)
            .readTimeout(40, TimeUnit.SECONDS)
            .addInterceptor(HeadersInterceptor())
            .addInterceptor(NetworkConnectionInterceptor(androidContext()))
            .addInterceptor(TokenInterceptor(get()))
            .also {
                if (BuildConfig.DEBUG) {
                    it.addInterceptor(
                        HttpLoggingInterceptor(ApiLogger())
                            .setLevel(HttpLoggingInterceptor.Level.BODY)
                    )
                }
            }
            .build()
    }

    single {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(get())
    }

    single {
        get<Retrofit.Builder>()
            // TODO ссылка на сервер
            .baseUrl("http://185.22.233.160:5001/api/v1/")
            .build()
    }

    single {
        get<Retrofit>().create(Api::class.java)
    }

}
