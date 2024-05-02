package com.arni.presentation.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.unit.sp

@Composable
fun CheckBoxMy(
    checked: State<Boolean>,
    onCheckedChange: (Boolean) -> Unit,
    text: String,
    enabled: Boolean
) {
    val checkedValue = checked.value
    Row(verticalAlignment = CenterVertically) {
        Checkbox(enabled = enabled, checked = checkedValue, onCheckedChange = onCheckedChange)
        Text(text, fontSize = 16.sp)
    }
}
