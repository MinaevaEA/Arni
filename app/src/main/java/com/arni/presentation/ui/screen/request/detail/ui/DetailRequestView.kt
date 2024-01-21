package com.arni.presentation.ui.screen.request.detail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arni.R
import com.arni.presentation.ui.components.PhotoLine
import com.arni.presentation.ui.components.TextFieldInput
import com.arni.presentation.ui.components.TextFieldSelector
import com.arni.presentation.ui.components.TextTitleToolbar
import pro.midev.mec.presentation.ui.style.ArniTheme

@Composable
fun DetailRequestView(
    state: DetailRequestState, eventConsumer: (DetailRequestEvent) -> Unit
) {
    var showSelectMediaOptionBs = remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ArniTheme.colors.neutral_0)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {

        TextTitleToolbar(
            title = state.item.namePatient,
            onBackPressed = { eventConsumer(DetailRequestEvent.onClickBackList) },
            actionsEnd = {
                TextButton(onClick = {}) {
                    Text(text = "Редактировать")
                }
            })
        LazyColumn(modifier = Modifier) {
            item {
                Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    TextFieldSelector(
                        label = stringResource(id = R.string.status_order),
                        onClick = {},
                        text = state.item.statusRequest.toString(),
                        enabled = state.enabled
                    )
                    TextFieldSelector(
                        label = stringResource(id = R.string.date_order),
                        onClick = {},
                        text = state.item.date,
                        enabled = state.enabled
                    )
                    TextFieldSelector(
                        label = stringResource(id = R.string.local_order),
                        onClick = {},
                        text = "",
                        enabled = state.enabled
                    )
                    TextFieldSelector(
                        label = stringResource(id = R.string.from_local),
                        onClick = {},
                        text = state.item.fromDepartament,
                        enabled = state.enabled
                    )
                    TextFieldSelector(
                        label = stringResource(id = R.string.to_local),
                        onClick = {},
                        text = state.item.toDepartament,
                        enabled = state.enabled
                    )
                    TextFieldSelector(
                        label = stringResource(id = R.string.begin_date),
                        onClick = {},
                        text = state.item.beginTime,
                        enabled = state.enabled
                    )
                    TextFieldSelector(
                        label = stringResource(id = R.string.end_date),
                        onClick = {},
                        text = state.item.endTime,
                        enabled = state.enabled
                    )
                    TextFieldSelector(
                        label = stringResource(id = R.string.draft_order),
                        onClick = {},
                        text = state.item.urgency,
                        enabled = state.enabled
                    )
                    TextFieldSelector(
                        label = stringResource(id = R.string.checking_order),
                        onClick = {},
                        text = state.item.nameExecutor,
                        enabled = state.enabled
                    )
                    TextFieldInput(
                        label = stringResource(id = R.string.name_patient_order),
                        text = state.item.namePatient,
                        enabled = state.enabled
                    )
                    TextFieldSelector(
                        label = stringResource(id = R.string.status_patient),
                        onClick = {},
                        text = state.item.statusPatient,
                        enabled = state.enabled
                    )
                    //todo разобраться с отключением на редактирование
                    TextFieldInput(
                        label = stringResource(id = R.string.comment_order),
                        text = state.item.description,
                        enabled = state.enabled
                    )
                    TextFieldInput(
                        label = stringResource(id = R.string.name_inicial),
                        text = state.item.nameExecutor,
                        enabled = state.enabled
                    )
                    //todo компонент фото
                    Text(
                        text = stringResource(id = R.string.photo), style = ArniTheme.typography.subhead.regular,
                        color = ArniTheme.colors.neutral_300,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    PhotoLine(
                        addPhotoAction = {
                            showSelectMediaOptionBs.value = true
                        },
                        deletePhotoAction = {},
                        photos = listOf("https://memepedia.ru/wp-content/uploads/2020/10/screenshot_11-3-360x270.png"),
                    )
                }
            }
        }
    }
}

@Composable
@Preview
private fun DetailRequestViewPreview() {
    ArniTheme {
        DetailRequestView(state = DetailRequestState(), eventConsumer = {})
    }
}
