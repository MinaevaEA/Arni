package com.arni.domain.usecase.selects

import com.arni.data.base.DataStatus
import com.arni.presentation.enum.StatusRoleHuman
import com.arni.presentation.model.human.PatientStatusHuman
import com.arni.presentation.model.human.RequestStatusHuman
import com.arni.presentation.model.human.UserHuman
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPatientStatusUseCase {
    suspend operator fun invoke(): Flow<DataStatus<List<PatientStatusHuman>>> = flow {
        emit(
            DataStatus.Success(
                listOf(
                    PatientStatusHuman(
                        id = 1,
                        status = "Сидячий"
                    ),
                    PatientStatusHuman(
                        id = 2,
                        status = "Лежачий"
                    ),
                )
            )
        )
    }
}
