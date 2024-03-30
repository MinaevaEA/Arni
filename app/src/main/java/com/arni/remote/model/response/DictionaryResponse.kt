package com.arni.remote.model.response

import com.arni.domain.model.DictionaryDomain
import com.arni.domain.model.InitiatorDomain
import com.google.gson.annotations.SerializedName

data class DictionaryResponse(
    @SerializedName("user_guid")
    val userId: String?,
    @SerializedName("divisioncollection")
    val division: List<DivisionResponse>,
    val initiator: InitiatorResponse?,
    @SerializedName("statusitemcollection")
    val statusItem: List<StatusItemResponse>?,
    @SerializedName("urgencycollection")
    val urgency: List<UrgencyResponse>?,
    @SerializedName("statuspatientcollection")
    val statusPatient: List<StatusPatientResponse>?,
)

fun DictionaryResponse.toDomain() = DictionaryDomain(
    userId = userId ?: "",
    division = division.toDomain() ?: listOf(),
    initiator = initiator?.toDomain() ?: InitiatorDomain("",""),
    statusItem = statusItem?.toDomain() ?: listOf(),
    urgency = urgency?.toDomain() ?: listOf(),
    statusPatient = statusPatient?.toDomain() ?: listOf()
)




