package com.arni.presentation.model.human

data class DictionaryHuman(
    val userId: String,
    val division: List<DivisionHuman>,
    val initiator: InitiatorHuman,
    val statusItem: List<RequestStatusHuman>,
    val urgency: List<UrgencyHuman>,
    val statusPatient: List<StatusPatientHuman>,
)
{
    companion object{
        fun getDefault() = DictionaryHuman(
           userId = "",
            division = listOf(),
            initiator = InitiatorHuman("",""),
            statusItem = listOf(),
            urgency = listOf(),
            statusPatient = listOf()
        )
    }
}
