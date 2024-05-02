package com.arni.domain.repository

import com.arni.data.base.DataStatus
import com.arni.remote.Api
import com.arni.remote.model.response.CheckedResponse

class RequestChangeRepositoryRemote(private val api: Api) : RequestRepository {
    suspend fun getCheckRequest(
        listId: String?,
        requestGuid: String,
    ): DataStatus<CheckedResponse> =
        handleRequest { api.getCheck(listId, requestGuid) }
}
