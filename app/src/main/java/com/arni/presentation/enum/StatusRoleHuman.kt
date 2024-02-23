package com.arni.presentation.enum

enum class StatusRoleHuman {
    EXECUTOR, ADMIN, INITIAL, EXECUTOR_INITIAL;

    companion object {
        fun parse(type: StatusRoleHuman) = when (type) {
            //TODO как будут приходить статусы с бэка
            EXECUTOR -> 1
            ADMIN -> 2
            INITIAL -> 3
            EXECUTOR_INITIAL -> 4
        }
    }
}
