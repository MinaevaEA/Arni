package com.arni.domain.usecase

import com.arni.data.base.DataStatus
import com.arni.data.base.mapTo
import com.arni.domain.model.toHuman
import com.arni.domain.repository.RequestChangeRepositoryRemote
import com.arni.domain.repository.RequestEditRepositoryRemote
import com.arni.presentation.model.human.CheckedHuman
import com.arni.presentation.model.human.EditRequestHuman
import com.arni.remote.model.response.toDomain
import com.arni.remote.model.response.toHuman
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRequestEditUseCase(private val repository: RequestEditRepositoryRemote) {
    suspend operator fun invoke(listId: String? = null, requestGuid: String): Flow<DataStatus<EditRequestHuman>> = flow {
     when (val requestResponse = repository.getEditItem(listId, requestGuid)) {
            is DataStatus.Success -> {
               requestResponse.mapTo {
                   it.toDomain().toHuman()
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
