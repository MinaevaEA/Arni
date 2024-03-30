package com.arni.domain.model

import com.arni.presentation.model.human.DictionaryHuman

data class DictionaryDomain(
    val userId: String,
    val division: List<DivisionDomain>,
    val initiator: InitiatorDomain,
    val statusItem: List<StatusItemDomain>,
    val urgency: List<UrgencyDomain>,
    val statusPatient: List<StatusPatientDomain>,
)
fun DictionaryDomain.toHuman() = DictionaryHuman(
    userId = userId,
    division = division.toHuman(),
    initiator = initiator.toHuman(),
    statusItem = statusItem.toHuman(),
    urgency = urgency.toHuman(),
    statusPatient = statusPatient.toHuman()
)
