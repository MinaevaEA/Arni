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
                        id = 1,
                        title = "Рабочая"
                    ),
                    RequestStatusHuman(
                        id = 1,
                        title = "Черновик"
                    ),
                    RequestStatusHuman(
                        id = 1,
                        title = "Завершена"
                    ),
                )
            )
        )
    }
}
