package com.arni.presentation.model.human

data class RequestHuman(
    val id: Long,
    val statusRequest: Int,
    val date: String,
    val fromDepartament: String,
    val toDepartament: String,
    val beginTime: String,
    val endTime: String,
    val urgency: String,
    val nameExecutor: String,
    val namePatient: String,
    val statusPatient: String,
    val description: String,
    val photo: List<String>
) {

    companion object {

        fun getDefault() = RequestHuman(
            id = 1,
            statusRequest = 1,
            date = "12.01.2024",
            fromDepartament = "Отделение 1",
            toDepartament = "Отделение 2",
            beginTime = "14:00",
            endTime = "17:00",
            urgency = "Э",
            nameExecutor = "Иванов Иван",
            namePatient = "Иванова Ольга",
            statusPatient = "Л",
            description = "Здесь описание",
            photo = listOf()
        )
    }
}
