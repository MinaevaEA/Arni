package com.arni.remote

import com.arni.remote.model.response.DataWrapper
import com.arni.remote.model.response.TokenResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {
    @FormUrlEncoded
    @POST("auth/token/login/")
    suspend fun getLogin(
       @Field("username") username: String,
       @Field("password") password: String
    ): DataWrapper<TokenResponse>
}
