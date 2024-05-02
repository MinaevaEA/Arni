package com.arni.domain.usecase

import com.arni.data.base.DataStatus
import com.arni.data.base.mapTo
import com.arni.domain.model.toHuman
import com.arni.domain.repository.RequestAddRepositoryRemote
import com.arni.presentation.model.human.CreateRequestHuman
import com.arni.presentation.model.human.RequestHuman
import com.arni.remote.model.response.toDomain

class GetRequestAddDetailUseCase(private val repository: RequestAddRepositoryRemote) {
    suspend operator fun invoke(listId: String, item: CreateRequestHuman): DataStatus<RequestHuman> {
        return when (val requestResponse = repository.getAddItem(listId, item)) {
            is DataStatus.Success -> {
               requestResponse
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
