package com.arni.domain.usecase.selects

import com.arni.data.base.DataStatus
import com.arni.presentation.enum.StatusRoleHuman
import com.arni.presentation.model.human.RequestStatusHuman
import com.arni.presentation.model.human.UserHuman
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetExecutorUseCase {
    suspend operator fun invoke(): Flow<DataStatus<List<UserHuman>>> = flow {
        emit(
            DataStatus.Success(
                listOf(
                    UserHuman(
                        userName = "Иван Васильев",
                        role = StatusRoleHuman.EXECUTOR,
                        subdivision = null
                    ),
                    UserHuman(
                        userName = "Олег Васильев",
                        role = StatusRoleHuman.EXECUTOR,
                        subdivision = null
                    ),
                    UserHuman(
                        userName = "Петр Петров",
                        role = StatusRoleHuman.EXECUTOR,
                        subdivision = null
                    ),
                )
            )
        )
    }
}
