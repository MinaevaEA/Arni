package com.arni.domain.usecase.request

import com.arni.data.base.DataStatus
import com.arni.data.base.mapTo
import com.arni.domain.base.BaseUseCase
import com.arni.domain.model.toHuman
import com.arni.domain.repository.DictionaryRepositoryRemote
import com.arni.presentation.model.human.DictionaryHuman
import com.arni.remote.model.response.toDomain

class GetDictionaryUseCase(
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
