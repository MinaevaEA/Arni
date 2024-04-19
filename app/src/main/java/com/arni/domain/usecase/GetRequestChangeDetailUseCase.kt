package com.arni.domain.usecase

import com.arni.data.base.DataStatus
import com.arni.data.base.mapTo
import com.arni.domain.model.toHuman
import com.arni.domain.repository.RequestChangeRepositoryRemote
import com.arni.domain.repository.RequestRepositoryRemote
import com.arni.presentation.model.human.CheckedHuman
import com.arni.presentation.model.human.ListRequestHuman
import com.arni.remote.model.response.toDomain
import com.arni.remote.model.response.toHuman

class GetRequestChangeDetailUseCase(private val repository: RequestChangeRepositoryRemote) {
    suspend operator fun invoke(listId: String? = null, requestGuid: String): DataStatus<CheckedHuman> {
        return when (val requestResponse = repository.getCheckRequest(listId, requestGuid)) {
            is DataStatus.Success -> {
               requestResponse.mapTo {
                   it.toHuman()
               }
            }

            is DataStatus.Error -> {
                DataStatus.Error(requestResponse.ex)
            }

            is DataStatus.Loading -> {
                requestResponse
            }
        }
    }
}
