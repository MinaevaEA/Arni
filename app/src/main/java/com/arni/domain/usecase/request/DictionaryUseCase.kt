package com.arni.domain.usecase.request

import com.arni.data.base.DataStatus
import com.arni.data.base.mapTo
import com.arni.data.local.keystorage.UserKeyStorage
import com.arni.domain.base.BaseUseCase
import com.arni.domain.model.toHuman
import com.arni.domain.repository.DictionaryRepositoryRemote
import com.arni.domain.repository.user.AuthRepositoryRemote
import com.arni.presentation.model.human.AuthHuman
import com.arni.presentation.model.human.DictionaryHuman
import com.arni.presentation.model.human.TokenHuman
import com.arni.presentation.model.human.toDomain
import com.arni.remote.model.response.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DictionaryUseCase(
    private val remoteRepository: DictionaryRepositoryRemote,
) : BaseUseCase {

    suspend operator fun invoke(): DataStatus<DictionaryHuman> {
        return when (val dictionaryResponse = remoteRepository.getDictionary()) {
            is DataStatus.Error -> {
                DataStatus.Error(dictionaryResponse.ex)
            }

            DataStatus.Loading -> {
                DataStatus.Loading
            }

            is DataStatus.Success -> {
                dictionaryResponse.mapTo {
                    it.toDomain().toHuman()
                }
            }
        }
    }
}
