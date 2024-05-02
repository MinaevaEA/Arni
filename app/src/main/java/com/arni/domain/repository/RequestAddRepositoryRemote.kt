package com.arni.domain.repository

import com.arni.data.base.DataStatus
import com.arni.presentation.model.human.CreateRequestHuman
import com.arni.presentation.model.human.toNewRequest
import com.arni.presentation.model.human.toRequest
import com.arni.remote.Api
import com.arni.remote.model.request.ContainerItemRequest
import com.arni.remote.model.request.DivisionRequest
import com.arni.remote.model.request.EditItemRequest

class RequestAddRepositoryRemote(private val api: Api) : RequestRepository {
    suspend fun getAddItem(
        listId: String,
        item: CreateRequestHuman
    ): DataStatus<Any> =
        handleRequest {
            api.addRequest(
                listId,
                ContainerItemRequest(
                    item =
                    EditItemRequest(
                        guid = item.guid,
                        date = item.date,
                        markdelete = false,
                        number = item.number,
                        division = item.division.toNewRequest(),
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
