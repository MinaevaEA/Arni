package com.arni.domain.usecase.selects

import com.arni.data.base.DataStatus
import com.arni.presentation.model.human.UrgencyHuman
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetSelectUrgentlyUseCase {
    suspend operator fun invoke(): Flow<DataStatus<List<UrgencyHuman>>> = flow {
        emit(
            DataStatus.Success(
                listOf(
                    UrgencyHuman(
                        guid = "",
                        name = "Планова"
                    ),
                    UrgencyHuman(
                        guid = "",
                        name = "Экстренная"
                    ),
                )
            )
        )
    }
}
