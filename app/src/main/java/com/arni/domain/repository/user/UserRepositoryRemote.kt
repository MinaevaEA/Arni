package com.arni.domain.repository.user

import com.arni.data.base.DataStatus
import com.arni.remote.Api
import com.arni.remote.model.response.TokenResponse


class UserRepositoryRemote(
    private val api: Api
) : UserRepository {
    suspend fun authorizationUser(
        username: String,
        password: String
    ): DataStatus<TokenResponse> = handleRequest {
        api.getLogin(username, password)
    }
}

