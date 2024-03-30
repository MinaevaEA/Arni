package com.arni.remote.model.response

import com.arni.domain.model.TokenDomain
import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("user_token")
    val token: String? = null,
   // val role: String
)
fun TokenResponse.toDomain() = TokenDomain(
    token = token ?: ""
)
