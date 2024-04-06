package com.arni.data.di

import com.arni.remote.Api
import com.arni.remote.utils.ApiLogger
import com.arni.remote.utils.HeadersInterceptor
import com.arni.remote.utils.NetworkConnectionInterceptor
import com.arni.remote.utils.TokenInterceptor
import com.google.gson.GsonBuilder
import com.tencent.mmkv.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

val remoteStorageModule = module {

    single {
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun checkClientTrusted(
                chain: Array<out X509Certificate>?,
                authType: String?
            ) {}

            override fun checkServerTrusted(
                chain: Array<out X509Certificate>?,
                authType: String?
            ) {}

            override fun getAcceptedIssuers() = arrayOf<X509Certificate>()
        })

        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, java.security.SecureRandom())

// Create an ssl socket factory with our all-trusting manager
        val sslSocketFactory = sslContext.socketFactory
        OkHttpClient.Builder()
            .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            .hostnameVerifier{ _, _ -> true }
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
            .baseUrl("https://1c.internal-inra.ru:12000/KA_BF_KUZ/hs/dispatcher/v1/")
            .build()
    }

    single {
        get<Retrofit>().create(Api::class.java)
    }

}
