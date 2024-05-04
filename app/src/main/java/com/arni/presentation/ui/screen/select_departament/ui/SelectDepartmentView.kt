package com.arni.presentation.ui.screen.select_departament.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arni.R
import com.arni.presentation.model.human.DepartmentHuman
import com.arni.presentation.model.human.DivisionHuman
import com.arni.presentation.ui.components.TextTitleToolbar
import pro.midev.mec.presentation.ui.style.ArniTheme

@Composable
fun SelectDepartmentView(
    departments: List<DepartmentHuman>,
    onSelect: (DepartmentHuman) -> Unit,
    onExit: () -> Unit,
) {

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding()
            .background(color = ArniTheme.colors.neutral_0)
    ) {

        TextTitleToolbar(onBackPressed = onExit, title = stringResource(id = R.string.add_departament))
        LazyColumn(modifier = Modifier) {

            itemsIndexed(departments) { _, item ->
                Row(
                    modifier = Modifier.clickable { onSelect(item) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .weight(1F)
                            .padding(vertical = 12.dp, horizontal = 20.dp),
                        text = item.name,
                        color = ArniTheme.colors.black_100,
                        style = ArniTheme.typography.body.regular
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun SelectStatusRequestViewPreview() {
    ArniTheme {
        //SelectDepartmentView(state = Loading, eventConsumer = {})
    }
}
