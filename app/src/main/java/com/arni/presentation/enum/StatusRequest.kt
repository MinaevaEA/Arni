package com.arni.presentation.enum

enum class StatusRequests {
    WORK, COMPLETED, DRAFT;

    companion object {
        fun parse(type: StatusRequests) = when (type) {
            //TODO как будут приходить статусы с бэка
            WORK -> "Рабочая"
            COMPLETED -> "Завершена"
            DRAFT -> "Черновик"
        }
    }
}
