package com.arni.domain.model

import com.arni.presentation.model.human.EditRequestHuman

data class EditRequestDomain(
    val item: RequestDomain
)
fun EditRequestDomain.toHuman() = EditRequestHuman(
    item = item.toHuman()
)
