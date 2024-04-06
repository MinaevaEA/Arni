package com.arni.domain.repository.user

import com.arni.data.base.DataStatus
import com.arni.remote.Api
import com.arni.remote.model.response.TokenResponse
import okhttp3.Credentials


class AuthRepositoryRemote(
    private val api: Api
) : UserRepository {
    suspend fun authorizationUser(login: String, password: String): DataStatus<TokenResponse> = handleRequest {
        api.getLogin(Credentials.basic(login, password))
    }
}

