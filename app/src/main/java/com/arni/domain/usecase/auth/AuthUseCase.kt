package com.arni.domain.usecase.auth

import com.arni.data.base.DataStatus
import com.arni.data.local.keystorage.UserKeyStorage
import com.arni.domain.base.BaseUseCase
import com.arni.domain.model.toHuman
import com.arni.domain.repository.user.AuthRepositoryRemote
import com.arni.presentation.model.human.AuthHuman
import com.arni.presentation.model.human.TokenHuman
import com.arni.presentation.model.human.toDomain
import com.arni.remote.model.response.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class AuthUseCase(
    private val remoteRepository: AuthRepositoryRemote,
    private val userKeyStorage: UserKeyStorage
) : BaseUseCase {

    operator fun invoke(user: AuthHuman): Flow<DataStatus<TokenHuman>> = flow {
        userKeyStorage.saveLogin(user.login)
        userKeyStorage.savePassword(user.password)
        when (val tokenResponse = remoteRepository.authorizationUser()/*(user.toDomain())*/) {

            is DataStatus.Error -> emit(DataStatus.Error(tokenResponse.ex))
            DataStatus.Loading -> {
                emit(DataStatus.Loading)
            }

            is DataStatus.Success -> {
                userKeyStorage.saveToken(tokenResponse.data.token ?: "")
                emit(DataStatus.Success(tokenResponse.data.toDomain().toHuman()))
            }
        }
    }
}
