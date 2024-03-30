package com.arni.presentation.model.human

import com.arni.domain.model.AuthDomain


data class AuthHuman(
    val login: String,
    val password: String
)

fun AuthHuman.toDomain() = AuthDomain(
    login = login,
    password = password
)
