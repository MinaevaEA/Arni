package com.arni.domain.usecase

import com.arni.data.base.DataStatus
import com.arni.data.base.mapTo
import com.arni.domain.repository.RequestEditRepositoryRemote
import com.arni.presentation.model.human.CheckedHuman
import com.arni.presentation.model.human.RequestHuman
import com.arni.remote.model.response.toHuman

class GetRequestEditUseCase(private val repository: RequestEditRepositoryRemote) {
    suspend operator fun invoke(listId: String, requestGuid: String, item: RequestHuman): DataStatus<CheckedHuman> {
     return when (val requestResponse = repository.getEditItem(listId, requestGuid, item)) {
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
