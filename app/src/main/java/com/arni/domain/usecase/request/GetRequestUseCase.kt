package com.arni.domain.usecase.request

import com.arni.data.base.DataStatus
import com.arni.presentation.model.human.RequestHuman
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRequestUseCase {
    suspend operator fun invoke(): Flow<DataStatus<List<RequestHuman>>> = flow {
        emit(
            DataStatus.Success(
                listOf(
                    RequestHuman(
                        id = 1,
                        statusRequest = 2,
                        date = "12.03.2024",
                        fromDepartament = "Отделение 1",
                        toDepartament = "Отделение 2",
                        beginTime = "14:00",
                        endTime = "17:00",
                        urgency = "Э",
                        nameExecutor = "Иванов Иван",
                        namePatient = "Иванова Ольга",
                        statusPatient = "Л",
                        description = "Здесь описание",
                        photo = listOf(),
                        nameDispatcher = "Иванов Иван"
                    ),
                    RequestHuman(
                        id = 1,
                        statusRequest = 1,
                        date = "1.01.2024",
                        fromDepartament = "Отделение 1",
                        toDepartament = "Отделение 2",
                        beginTime = "14:00",
                        endTime = "17:00",
                        urgency = "Э",
                        nameExecutor = "Петров Иван",
                        namePatient = "Иванова Ольга",
                        statusPatient = "Л",
                        description = "Здесь описание",
                        photo = listOf(),
                        nameDispatcher = "Иванов Иван"
                    ),
                    RequestHuman(
                        id = 1,
                        statusRequest = 1,
                        date = "12.01.2024",
                        fromDepartament = "Отделение 1",
                        toDepartament = "Отделение 2",
                        beginTime = "14:00",
                        endTime = "17:00",
                        urgency = "Э",
                        nameExecutor = "Иванов Иван",
                        namePatient = "Иванова Ольга",
                        statusPatient = "Л",
                        description = "Здесь описание",
                        photo = listOf(),
                        nameDispatcher = "Иванов Иван"
                    ),
                    RequestHuman(
                        id = 1,
                        statusRequest = 2,
                        date = "12.01.2024",
                        fromDepartament = "Отделение 1",
                        toDepartament = "Отделение 2",
                        beginTime = "14:00",
                        endTime = "17:00",
                        urgency = "Э",
                        nameExecutor = "Иванов Иван",
                        namePatient = "Иванова Ольга",
                        statusPatient = "Л",
                        description = "Здесь описание",
                        photo = listOf(),
                        nameDispatcher = "Иванов Иван"
                    ),
                    RequestHuman(
                        id = 1,
                        statusRequest = 3,
                        date = "12.04.2024",
                        fromDepartament = "Отделение 1",
                        toDepartament = "Отделение 2",
                        beginTime = "14:00",
                        endTime = "17:00",
                        urgency = "Э",
                        nameExecutor = "Иванов Иван",
                        namePatient = "Иванова Ольга",
                        statusPatient = "Л",
                        description = "Здесь описание",
                        photo = listOf(),
                        nameDispatcher = "Иванов Иван"
                    ),
                )
            )
        )
    }

}
