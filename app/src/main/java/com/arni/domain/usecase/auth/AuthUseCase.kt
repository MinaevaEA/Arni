package com.arni.domain.usecase.auth

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.arni.data.base.CompletableStatus
import com.arni.data.base.DataStatus
import com.arni.domain.base.BaseUseCase
import com.arni.domain.repository.user.UserRepositoryRemote
import com.arni.data.local.keystorage.UserKeyStorage


class AuthUseCase(
    private val remoteRepository: UserRepositoryRemote,
    private val userKeyStorage: UserKeyStorage
) : BaseUseCase {

    operator fun invoke(username: String, password: String): Flow<CompletableStatus> = flow {
        when (val tokenResponse = remoteRepository.authorizationUser(username, password)) {
            is DataStatus.Error -> emit(CompletableStatus.Error(tokenResponse.ex))
            DataStatus.Loading -> {
                emit(CompletableStatus.Loading)
            }

            is DataStatus.Success -> {
                userKeyStorage.saveToken(tokenResponse.data.token ?: "")
                userKeyStorage.saveRole(tokenResponse.data.role ?: "")
                emit(CompletableStatus.Success)
            }
        }
    }
}
