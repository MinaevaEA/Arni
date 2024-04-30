package com.arni.domain.usecase.request

import com.arni.data.base.DataStatus
import com.arni.data.base.mapTo
import com.arni.domain.model.toHuman
import com.arni.domain.repository.ChangeRequestRepositoryRemote
import com.arni.domain.repository.RequestRepositoryRemote
import com.arni.presentation.model.human.ListRequestHuman
import com.arni.remote.model.response.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber

class GetChangeRequestUseCase(private val repository: ChangeRequestRepositoryRemote) {
    suspend operator fun invoke(
        limit: Int, listId: String? = null, divisionGuid: String, pointRef: String? = null,
        pointDate: String? = null
    ): DataStatus<ListRequestHuman> {
        return when (val requestResponse =
            repository.getAllChangeRequest(limit, listId, divisionGuid, pointRef, pointDate)) {
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
