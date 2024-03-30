package com.arni.domain.usecase.selects

import com.arni.data.base.DataStatus
import com.arni.presentation.model.human.RequestStatusHuman
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetSelectStatusRequestUseCase {
    suspend operator fun invoke(): Flow<DataStatus<List<RequestStatusHuman>>> = flow {
        emit(
            DataStatus.Success(
                listOf(
                    RequestStatusHuman(
                        guid = "",
                        name = "Рабочая"
                    ),
                    RequestStatusHuman(
                        guid = "",
                        name = "Черновик"
                    ),
                    RequestStatusHuman(
                        guid = "",
                        name = "Завершена"
                    ),
                )
            )
        )
    }
}
