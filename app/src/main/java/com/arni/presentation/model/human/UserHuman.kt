package com.arni.presentation.model.human

data class UserHuman(val userName: String) {

    companion object {

        fun getDefault() = UserHuman(
            userName = ""
        )
    }
}
