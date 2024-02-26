package com.arni.domain.usecase.selects

import com.arni.data.base.DataStatus
import com.arni.presentation.model.human.RequestStatusHuman
import com.arni.presentation.model.human.UrgentlyHuman
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetSelectUrgentlyUseCase {
    suspend operator fun invoke(): Flow<DataStatus<List<UrgentlyHuman>>> = flow {
        emit(
            DataStatus.Success(
                listOf(
                    UrgentlyHuman(
                        id = 1,
                        title = "Планова"
                    ),
                    UrgentlyHuman(
                        id = 1,
                        title = "Экстренная"
                    ),
                )
            )
        )
    }
}
