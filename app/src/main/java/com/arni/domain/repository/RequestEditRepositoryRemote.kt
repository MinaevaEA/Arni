package com.arni.domain.repository

import com.arni.data.base.DataStatus
import com.arni.presentation.model.human.RequestHuman
import com.arni.presentation.model.human.toRequest
import com.arni.remote.Api
import com.arni.remote.model.request.ContainerItemRequest
import com.arni.remote.model.request.EditItemRequest
import com.arni.remote.model.response.CheckedResponse
import com.arni.remote.model.response.toDataStatus

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
                        guid = item.guid,
                        date = item.date,
                        markdelete = item.markdelete,
                        number = item.number,
                        division = item.division.toRequest(),
                        departamentFrom = item.departamentFrom.toRequest(),
                        departamentTo = item.departamentTo.toRequest(),
                        dateStart = item.startDate,
                        dateEnd = item.endDate,
                        dispatcher = item.dispatcher.toRequest(),
                        urgency = item.urgency.toRequest(),
                        initiator = item.initiator.toRequest(),
                        statusPatient = item.statusPatient.toRequest(),
                        statusItem = item.statusRequest.toRequest(),
                        comment = item.comment,
                        patients = item.patients.toRequest(),
                        executor = item.executors?.toRequest()
                    )
                )
            )
        }
}
