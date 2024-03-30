package com.arni.domain.usecase.selects

import com.arni.data.base.DataStatus
import com.arni.presentation.model.human.PatientHuman
import com.arni.presentation.model.human.StatusPatientHuman
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPatientStatusUseCase {
    suspend operator fun invoke(): Flow<DataStatus<List<StatusPatientHuman>>> = flow {
        emit(
            DataStatus.Success(
                listOf(
                    PatientHuman(
                        name = "Сидячий"
                    ),
                    PatientHuman(
                        name = "Лежачий"
                    ),
                )
            )
        )
    }
}
