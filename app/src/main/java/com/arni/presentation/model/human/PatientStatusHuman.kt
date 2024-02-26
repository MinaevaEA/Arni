package com.arni.presentation.model.human

data class PatientStatusHuman(
    val id: Int?,
    val status: String
){
    companion object{
        fun getDefault() = PatientStatusHuman(id = null, status = "")
    }
}
