package com.arni.presentation.model.human


data class SubdivisionHuman(
    val id: Int? = null,
    val title: String? = null,
    val departaments: List<DepartmentHuman>
){
    companion object{
        fun getDefault() = SubdivisionHuman(
            id = null,
            title = null,
            departaments = listOf(DepartmentHuman(guid = "", "Отделение 1"), DepartmentHuman(guid = "", "Отделение 2"), DepartmentHuman(guid = "", "Отделение 1"))
        )
    }
}

