package com.arni.presentation.model.human

import com.arni.presentation.enum.StatusRoleHuman
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class UserHuman(
    val userName: String,
    val role: StatusRoleHuman = StatusRoleHuman.ADMIN,
    val subdivision: String?
) : Serializable {

    companion object {

        fun getDefault() = UserHuman(
            userName = "",
            role = StatusRoleHuman.ADMIN,
            subdivision = ""
        )
    }
}
