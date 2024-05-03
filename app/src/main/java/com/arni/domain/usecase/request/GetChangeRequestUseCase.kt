package com.arni.domain.usecase.request

import com.arni.data.base.DataStatus
import com.arni.data.base.mapTo
import com.arni.domain.model.toHuman
import com.arni.domain.repository.ChangeRequestRepositoryRemote
import com.arni.presentation.model.human.ListRequestHuman
import com.arni.remote.model.response.toDomain

class GetChangeRequestUseCase(private val repository: ChangeRequestRepositoryRemote) {
    suspend operator fun invoke(
        limit: Int, listId: String? = null, divisionGuid: String, pointRef: String? = null,
        pointDate: String? = null, onlycheck: Boolean
    ): DataStatus<ListRequestHuman> {
        return when (val requestResponse =
            repository.getUp(limit, listId, divisionGuid, onlycheck, pointRef, pointDate)) {
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
