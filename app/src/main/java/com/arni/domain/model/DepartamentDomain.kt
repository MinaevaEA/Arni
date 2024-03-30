package com.arni.domain.model

import com.arni.presentation.model.human.DepartmentHuman


data class DepartamentDomain(
    val guid: String,
    val name: String
)

fun DepartamentDomain.toHuman() = DepartmentHuman(
   guid = guid,
    name = name
)
 fun List<DepartamentDomain>.toHuman() = map { it.toHuman() }
