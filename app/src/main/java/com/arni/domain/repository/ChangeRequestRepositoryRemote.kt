package com.arni.domain.repository

import com.arni.data.base.DataStatus
import com.arni.remote.Api
import com.arni.remote.model.response.ListRequestResponse

class ChangeRequestRepositoryRemote(private val api: Api) : RequestRepository {
    suspend fun getUp(
        limit: Int,
        listId: String?,
        divisionitem: String,
        onlycheck: Boolean,
        pointRef: String?,
        pointDate: String?
    ): DataStatus<ListRequestResponse> =
        handleRequest {
            api.getUp(limit, listId, divisionitem, onlycheck = onlycheck, pointRef, pointDate) }
}
