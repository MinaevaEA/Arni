package com.arni.domain.repository.user

import com.arni.data.base.DataStatus
import com.arni.domain.model.AuthDomain
import com.arni.domain.model.toRequest
import com.arni.remote.Api
import com.arni.remote.model.response.TokenResponse


class AuthRepositoryRemote(
    private val api: Api
) : UserRepository {
    suspend fun authorizationUser(): DataStatus<TokenResponse> = handleRequest {
        api.getLogin()
    }
}

