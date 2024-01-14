package com.arni.presentation.enum

enum class StatusRequests {
    WORK, COMPLETED, DRAFT;

    companion object {
        fun parse(type: StatusRequests) = when (type) {
            //TODO как будут приходить статусы с бэка
            WORK -> 1
            COMPLETED -> 2
            DRAFT -> 3
        }
    }
}
