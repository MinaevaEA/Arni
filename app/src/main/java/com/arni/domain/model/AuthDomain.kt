package com.arni.domain.model

import com.arni.remote.model.request.AuthRequest


data class AuthDomain(
    val login: String,
    val password: String
)

fun AuthDomain.toRequest() = AuthRequest(
    login = login,
    password = password
)
