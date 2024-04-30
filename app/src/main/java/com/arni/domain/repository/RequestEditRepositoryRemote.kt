package com.arni.domain.repository

import com.arni.data.base.DataStatus
import com.arni.presentation.model.human.RequestHuman
import com.arni.presentation.model.human.toRequest
import com.arni.remote.Api
import com.arni.remote.model.request.ContainerItemRequest
import com.arni.remote.model.request.EditItemRequest
import com.arni.remote.model.response.CheckedResponse

class RequestEditRepositoryRemote(private val api: Api) : RequestRepository {
    suspend fun getEditItem(
        listId: String,
        requestGuid: String,
        item: RequestHuman
    ): DataStatus<CheckedResponse> =
        handleRequest {
            api.getChangeItem(
                listId,
                requestGuid,
                ContainerItemRequest(
                    item =
                    EditItemRequest(
                        item.guid,
                        item.date,
                        item.number,
                        item.division.toRequest(),
                        item.departamentFrom.toRequest(),
                        item.departamentTo.toRequest(),
                        item.startDate,
                        item.endDate,
                        item.dispatcher.toRequest(),
                        item.urgency.toRequest(),
                        item.initiator.toRequest(),
                        item.statusPatient.toRequest(),
                        item.statusRequest.toRequest(),
                        item.comment,
                        item.patients.toRequest(),
                        item.executors?.toRequest()
                    )
                )
            )
        }
}
