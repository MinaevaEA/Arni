package com.arni.domain.usecase.request

import com.arni.data.base.DataStatus
import com.arni.data.base.mapTo
import com.arni.domain.model.toHuman
import com.arni.domain.repository.RequestRepositoryRemote
import com.arni.presentation.model.human.ListRequestHuman
import com.arni.remote.model.response.toDomain

class GetRequestUseCase(private val repository: RequestRepositoryRemote) {
    suspend operator fun invoke(limit: Int, listId: String, divisionGuid: String
    ): DataStatus<ListRequestHuman> {
        return when (val requestResponse = repository.getAllRequest(limit, listId, divisionGuid)) {
            is DataStatus.Success -> {
                requestResponse.mapTo { it.toDomain().toHuman() }
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
