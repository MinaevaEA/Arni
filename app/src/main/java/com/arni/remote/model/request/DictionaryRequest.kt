package com.arni.remote.model.request

import com.google.gson.annotations.SerializedName

data class DictionaryRequest(
    @SerializedName("user_guid")
    val userId: String?,
    @SerializedName("divisioncollection")
    val division: List<DivisionRequest>,
    val initiator: InitiatorRequest?,
    @SerializedName("statusitemcollection")
    val statusItem: List<StatusItemRequest>?,
    @SerializedName("urgencycollection")
    val urgency: List<UrgencyRequest>?,
    @SerializedName("statuspatientcollection")
    val statusPatient: List<StatusPatientRequest>?,
)




