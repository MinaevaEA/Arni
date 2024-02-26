package com.arni.presentation.model.human


data class SubdivisionHuman(
    val id: Int? = null,
    val title: String? = null,
    val departaments: List<DepartamentHuman>
){
    companion object{
        fun getDefault() = SubdivisionHuman(
            id = null,
            title = null,
            departaments = listOf(DepartamentHuman(id = 1, "Отделение 1"), DepartamentHuman(id = 1, "Отделение 2"), DepartamentHuman(id = 1, "Отделение 1"))
        )
    }
}

