package com.arni.remote.model.request

import com.google.gson.annotations.SerializedName

data class DivisionRequest(
    @SerializedName("guid")
    val guid: String?,
    val name: String?,
    val role: String?,
    val executors: List<ExecutorRequest>?,
    var department: List<DepartmentRequest>?,
)
