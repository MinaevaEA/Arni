package com.arni.domain.usecase.request

import com.arni.data.base.DataStatus
import com.arni.presentation.enum.StatusRoleHuman
import com.arni.presentation.model.human.PatientStatusHuman
import com.arni.presentation.model.human.RequestHuman
import com.arni.presentation.model.human.RequestStatusHuman
import com.arni.presentation.model.human.UrgentlyHuman
import com.arni.presentation.model.human.UserHuman
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRequestUseCase {
    suspend operator fun invoke(): Flow<DataStatus<List<RequestHuman>>> = flow {
        emit(
            DataStatus.Success(
                listOf(
                    RequestHuman(
                        id = 1,
                        statusRequest = RequestStatusHuman(1,""),
                        date = "12.03.2024",
                        fromDepartament = "Отделение 1",
                        toDepartament = "Отделение 2",
                        beginTime = "14:00",
                        endTime = "17:00",
                        urgently = UrgentlyHuman(1, "Э"),
                        nameExecutor = UserHuman("Ivan", StatusRoleHuman.ADMIN,""),
                        namePatient = "Иванова Ольга",
                        statusPatient = PatientStatusHuman( 1, "Л"),
                        description = "Здесь описание",
                        photo = listOf(),
                        nameDispatcher = "Иванов Иван"
                    ),
                    RequestHuman(
                        id = 1,
                        statusRequest = RequestStatusHuman(1,""),
                        date = "1.01.2024",
                        fromDepartament = "Отделение 1",
                        toDepartament = "Отделение 2",
                        beginTime = "14:00",
                        endTime = "17:00",
                        urgently = UrgentlyHuman(1, "Э"),
                        nameExecutor = UserHuman("Ivan", StatusRoleHuman.ADMIN,""),
                        namePatient = "Иванова Ольга",
                        statusPatient = PatientStatusHuman( 1, "Л"),
                        description = "Здесь описание",
                        photo = listOf(),
                        nameDispatcher = "Иванов Иван"
                    ),
                    RequestHuman(
                        id = 1,
                        statusRequest = RequestStatusHuman(1,""),
                        date = "12.01.2024",
                        fromDepartament = "Отделение 1",
                        toDepartament = "Отделение 2",
                        beginTime = "14:00",
                        endTime = "17:00",
                        urgently = UrgentlyHuman(1, "Э"),
                        nameExecutor = UserHuman("Ivan", StatusRoleHuman.ADMIN,""),
                        namePatient = "Иванова Ольга",
                        statusPatient = PatientStatusHuman( 1, "Л"),
                        description = "Здесь описание",
                        photo = listOf(),
                        nameDispatcher = "Иванов Иван"
                    ),
                    RequestHuman(
                        id = 1,
                        statusRequest = RequestStatusHuman(1,""),
                        date = "12.01.2024",
                        fromDepartament = "Отделение 1",
                        toDepartament = "Отделение 2",
                        beginTime = "14:00",
                        endTime = "17:00",
                        urgently = UrgentlyHuman(1, "Э"),
                        nameExecutor = UserHuman("Ivan", StatusRoleHuman.ADMIN,""),
                        namePatient = "Иванова Ольга",
                        statusPatient = PatientStatusHuman( 1, "Л"),
                        description = "Здесь описание",
                        photo = listOf(),
                        nameDispatcher = "Иванов Иван"
                    ),
                    RequestHuman(
                        id = 1,
                        statusRequest = RequestStatusHuman(1,""),
                        date = "12.04.2024",
                        fromDepartament = "Отделение 1",
                        toDepartament = "Отделение 2",
                        beginTime = "14:00",
                        endTime = "17:00",
                        urgently = UrgentlyHuman(1, "Э"),
                        nameExecutor = UserHuman("Ivan", StatusRoleHuman.ADMIN,""),
                        namePatient = "Иванова Ольга",
                        statusPatient = PatientStatusHuman( 1, "Л"),
                        description = "Здесь описание",
                        photo = listOf(),
                        nameDispatcher = "Иванов Иван"
                    ),
                )
            )
        )
    }

}
