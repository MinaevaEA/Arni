package com.arni.domain.model

import com.arni.presentation.model.human.TokenHuman

data class TokenDomain(
    val token: String,
)

fun TokenDomain.toHuman() = TokenHuman(
    token = token ?: ""
)
