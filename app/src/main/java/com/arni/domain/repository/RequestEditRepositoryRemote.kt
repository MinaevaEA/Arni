package com.arni.domain.repository

import com.arni.data.base.DataStatus
import com.arni.remote.Api
import com.arni.remote.model.response.CheckedResponse
import com.arni.remote.model.response.EditRequestResponse
import com.arni.remote.model.response.ListRequestResponse

class RequestEditRepositoryRemote(private val api: Api) : RequestRepository {
    suspend fun getEditItem(
        listId: String?,
        requestGuid: String,
    ): DataStatus<EditRequestResponse> =
        handleRequest { api.getChangeItem(listId, requestGuid) }
}
