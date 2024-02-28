package com.arni.presentation.ui.screen.request.create.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arni.R
import com.arni.presentation.enum.StatusRequests
import com.arni.presentation.ui.components.PhotoLine
import com.arni.presentation.ui.components.TextFieldInput
import com.arni.presentation.ui.components.TextFieldSelector
import com.arni.presentation.ui.components.TextTitleToolbar
import pro.midev.mec.presentation.ui.style.ArniTheme

@Composable
fun CreateRequestView(
    state: CreateRequestState, eventConsumer: (CreateRequestEvent) -> Unit
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
            title = stringResource(R.string.title_add_screen),
            onBackPressed = { eventConsumer(CreateRequestEvent.onClickBack) },
            actionsEnd = {
                IconButton(
                    enabled = state.isEnabledButton,
                    onClick = { /*eventConsumer(DetailRequestEvent.onClickToolbarButton(!state.enabled))*/ }
                ) {
                    Icon(painter = painterResource(R.drawable.ic_check_mark), contentDescription = "")
                }
            })
        LazyColumn() {
            item {
                Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    TextFieldSelector(
                        label = stringResource(id = R.string.status_order),
                        onClick = { eventConsumer(CreateRequestEvent.onClickSelectStatus) },
                        text = when (state.item.statusRequest?.id) {
                            StatusRequests.parse(StatusRequests.WORK) -> "Рабочая"
                            StatusRequests.parse(StatusRequests.DRAFT) -> "Черновик"
                            StatusRequests.parse(StatusRequests.COMPLETED) -> "Завершена"
                            else -> {
                                ""
                            }
                        }
                    )
                    TextFieldSelector(
                        label = stringResource(id = R.string.date_order),
                        onClick = { eventConsumer(CreateRequestEvent.onClickSelectorDate) },
                        text = state.item.date ?: ""
                    )
                    TextFieldSelector(
                        label = stringResource(id = R.string.time_order),
                        onClick = { eventConsumer(CreateRequestEvent.onClickSelectorTime) },
                        text = state.item.date ?: ""
                    )
                    TextFieldSelector(
                        label = stringResource(id = R.string.local_order),
                        onClick = { eventConsumer(CreateRequestEvent.onClickSelectsubDivision) },
                        //todo откуда берется подразделение
                        text = state.human.subdivision ?: ""
                    )
                    TextFieldSelector(
                        label = stringResource(id = R.string.from_local),
                        onClick = { eventConsumer(CreateRequestEvent.onClickSelectDepartament(state.subdivisionHuman.departaments)) },
                        text = state.item.fromDepartament ?: ""
                    )
                    TextFieldSelector(
                        label = stringResource(id = R.string.to_local),
                        onClick = { eventConsumer(CreateRequestEvent.onClickSelectDepartament(state.subdivisionHuman.departaments)) },
                        text = state.item.toDepartament ?: ""
                    )
                    TextFieldSelector(
                        label = stringResource(id = R.string.begin_date),
                        onClick = { eventConsumer(CreateRequestEvent.onClickSelectorTime) },
                        text = state.item.beginTime ?: ""
                    )
                    TextFieldSelector(
                        label = stringResource(id = R.string.end_date),
                        onClick = { eventConsumer(CreateRequestEvent.onClickSelectorTime) },
                        text = state.item.endTime ?: ""
                    )
                    TextFieldSelector(
                        label = stringResource(id = R.string.draft_order),
                        onClick = { eventConsumer(CreateRequestEvent.onClickSelectUrgently) },
                        text = state.item.urgently?.title ?: ""
                    )
                    TextFieldSelector(
                        label = stringResource(id = R.string.checking_order),
                        onClick = { eventConsumer(CreateRequestEvent.onClickSelectExecutor) },
                        text = state.item.nameExecutor?.userName ?: ""
                    )
                    TextFieldInput(
                        label = stringResource(id = R.string.name_patient_order),
                        enabled = true,
                        text = state.item.namePatient ?: ""
                    )
                    TextFieldSelector(
                        label = stringResource(id = R.string.status_patient),
                        onClick = { eventConsumer(CreateRequestEvent.onClickSelectStatusPatient) },
                        text = state.item.statusPatient?.status ?: ""
                    )
                    TextFieldInput(
                        label = stringResource(id = R.string.comment_order),
                        enabled = true,
                        text = state.item.description ?: ""
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
        CreateRequestView(state = CreateRequestState(""), eventConsumer = {})
    }
}
