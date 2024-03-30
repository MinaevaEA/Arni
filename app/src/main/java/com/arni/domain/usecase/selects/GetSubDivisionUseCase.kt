package com.arni.domain.usecase.selects

import com.arni.data.base.DataStatus
import com.arni.presentation.model.human.DepartmentHuman
import com.arni.presentation.model.human.SubdivisionHuman
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetSubDivisionUseCase {
    suspend operator fun invoke(): Flow<DataStatus<List<SubdivisionHuman>>> = flow {
        emit(
            DataStatus.Success(
                listOf(
                    SubdivisionHuman(
                        id = 1,
                        title = "Подразделение 1",
                        listOf(DepartmentHuman(guid = "", "Отделение 1"), DepartmentHuman(guid = "", "Отделение 2"), DepartmentHuman(guid = "", "Отделение 1"))
                    ),
                    SubdivisionHuman(
                        id = 1,
                        title = "Подразделение 2",
                        listOf(DepartmentHuman(guid = "", "Отделение 1"), DepartmentHuman(guid = "", "Отделение 2"), DepartmentHuman(guid = "", "Отделение 1"))

                    ),
                    SubdivisionHuman(
                        id = 1,
                        title = "Подразделение 3",
                        listOf(DepartmentHuman(guid = "", "Отделение 1"), DepartmentHuman(guid = "", "Отделение 2"), DepartmentHuman(guid = "", "Отделение 1"))
                    ),
                    SubdivisionHuman(
                        id = 1,
                        title = "Подразделение 4",
                        listOf(DepartmentHuman(guid = "", "Отделение 1"), DepartmentHuman(guid = "", "Отделение 2"), DepartmentHuman(guid = "", "Отделение 1"))
                    )
                )
            )
        )
    }
}
