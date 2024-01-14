package com.arni.remote.model

import com.arni.remote.model.request.UserRequest
import com.google.firebase.crashlytics.buildtools.reloc.javax.annotation.concurrent.Immutable

@Immutable
data class UserForm(
    val userName: String,
    val password: String
){
    companion object {

        fun getEmpty() = UserForm(
           userName = "",
            password = ""
        )

    }
}

fun UserForm.toRequest() = UserRequest(
    userName = userName,
    password = password
)

