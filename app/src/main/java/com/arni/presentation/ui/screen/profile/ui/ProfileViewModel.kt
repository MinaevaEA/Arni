package com.arni.presentation.ui.screen.profile.ui

import com.arni.presentation.base.BaseViewModel

class ProfileViewModel:
    BaseViewModel<ProfileState, ProfileEvent, ProfileAction>(
        ProfileState(v = "")) {
    override fun obtainEvent(event: ProfileEvent) {
        TODO("Not yet implemented")
    }
}

