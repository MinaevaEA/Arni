package com.arni.presentation.model.human

import com.arni.presentation.enum.StatusRoleHuman

data class UserHuman(val userName: String, val role: StatusRoleHuman = StatusRoleHuman.ADMIN, val subdivision: String? = "Подразделение") {

    companion object {

        fun getDefault() = UserHuman(
            userName = "",
            role = StatusRoleHuman.ADMIN
        )
    }
}
