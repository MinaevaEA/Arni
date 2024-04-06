package com.arni.domain.repository

import com.arni.data.base.DataStatus
import com.arni.remote.Api
import com.arni.remote.model.response.ListRequestResponse

class RequestRepositoryRemote(private val api: Api) : RequestRepository {
    suspend fun getAllRequest(
        limit: Int,
        listId: String?,
        divisionitem: String,
        pointRef: String?,
        pointDate: String?
    ): DataStatus<ListRequestResponse> =
        handleRequest { api.getAllRequest(limit, listId, divisionitem, pointRef, pointDate) }
}
