package com.arni.presentation.model.human

import com.arni.domain.model.AuthDomain


data class AuthHuman(
    val phone: String,
    val password: String
)

fun AuthHuman.toDomain() = AuthDomain(
    login = phone,
    password = password
)
