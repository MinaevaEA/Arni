package com.arni.presentation.ui.screen.request.detail.ui

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
import androidx.compose.material.TextButton
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
import com.arni.presentation.enum.StatusRoleHuman
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
            title = "",
            onBackPressed = { eventConsumer(DetailRequestEvent.onClickBackList) },
            actionsEnd = {
                IconButton(
                    enabled = state.isEditRequest && state.isEnabledButton,
                    onClick = { eventConsumer(DetailRequestEvent.onClickToolbarButton(!state.enabled)) }
                ) {
                    Icon(painter = if (!state.enabled) painterResource(R.drawable.ic_chat_pen) else painterResource(R.drawable.ic_check_mark), contentDescription = "")
                }
            })
        LazyColumn(modifier = Modifier) {
            item {
                Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    TextFieldSelector(
                        label = stringResource(id = R.string.status_order),
                        onClick = {},
                        text = when (state.item.statusRequest) {
                            StatusRequests.parse(StatusRequests.WORK) -> "Рабочая"
                            StatusRequests.parse(StatusRequests.DRAFT) -> "Черновик"
                            StatusRequests.parse(StatusRequests.COMPLETED) -> "Завершена"
                            else -> {""}
                        } ,
                            enabled = if (state.human.role != StatusRoleHuman.INITIAL) state.enabled else false
                                )
                                TextFieldSelector(
                                    label = stringResource(id = R.string.date_order),
                                    onClick = {},
                                    text = state.item.date ?: "",
                                    enabled = if (state.human.role == StatusRoleHuman.EXECUTOR || state.human.role == StatusRoleHuman.EXECUTOR_INITIAL) false else state.enabled
                                )

                            TextFieldSelector(
                                label = stringResource(id = R.string.time_order),
                                onClick = {},
                                text = state.item.date ?: "",
                                enabled = if (state.human.role == StatusRoleHuman.EXECUTOR || state.human.role == StatusRoleHuman.EXECUTOR_INITIAL) false else state.enabled
                            )
                                    TextFieldSelector (
                                    label = stringResource(id = R.string.local_order),
                            onClick = {},
                            text = state.human.subdivision ?: "",
                            enabled =
                                if (state.human.role == StatusRoleHuman.EXECUTOR || state.human.role == StatusRoleHuman.INITIAL || state.human.role == StatusRoleHuman.EXECUTOR_INITIAL) false else state.enabled
                                )
                                TextFieldSelector(
                                    label = stringResource(id = R.string.from_local),
                                    onClick = {},
                                    text = state.item.fromDepartament ?: "",
                                    enabled = if (state.human.role == StatusRoleHuman.EXECUTOR || state.human.role == StatusRoleHuman.EXECUTOR_INITIAL) false else state.enabled
                                )

                            TextFieldSelector(
                                label = stringResource(id = R.string.to_local),
                                onClick = {},
                                text = state.item.toDepartament ?: "",
                                enabled = if (state.human.role == StatusRoleHuman.EXECUTOR || state.human.role == StatusRoleHuman.EXECUTOR_INITIAL) false else state.enabled
                            )
                                    TextFieldSelector (
                                    label = stringResource(id = R.string.begin_date),
                            onClick = {},
                            text = state.item.beginTime ?: "",
                            enabled = if (state.human.role != StatusRoleHuman.INITIAL) state.enabled else false
                                )
                                TextFieldSelector(
                                    label = stringResource(id = R.string.end_date),
                                    onClick = {},
                                    text = state.item.endTime ?: "",
                                    enabled = if (state.human.role != StatusRoleHuman.INITIAL) state.enabled else false
                                )

                            TextFieldInput(
                                label = stringResource(id = R.string.name_dispatcher),
                                text = state.item.nameDispatcher ?: "",
                                enabled = if (state.human.role != StatusRoleHuman.INITIAL) state.enabled else false
                            )
                                    TextFieldSelector (
                                    label = stringResource(id = R.string.draft_order),
                            onClick = {},
                            text = state.item.urgency ?: "",
                            enabled =
                                if (state.human.role == StatusRoleHuman.EXECUTOR || state.human.role == StatusRoleHuman.EXECUTOR_INITIAL) false else state.enabled
                                )
                                TextFieldInput(
                                    label = stringResource(id = R.string.name_patient_order),
                                    text = state.item.namePatient ?: "",
                                    enabled = state.enabled
                                )

                            TextFieldSelector(
                                label = stringResource(id = R.string.checking_order),
                                onClick = {},
                                text = state.item.nameExecutor ?: "",
                                enabled = if (state.human.role == StatusRoleHuman.EXECUTOR || state.human.role == StatusRoleHuman.INITIAL) false else state.enabled
                            )
                                    TextFieldSelector (
                                    label = stringResource(id = R.string.status_patient),
                            onClick = {},
                            text = state.item.statusPatient ?: "",
                            enabled = state.enabled
                                )
                                TextFieldInput(
                                    label = stringResource(id = R.string.name_inicial),
                                    text = state.item.nameExecutor ?: "",
                                    enabled = if (state.human.role != StatusRoleHuman.ADMIN) false else state.enabled
                                )

                            TextFieldInput(
                                label = stringResource(id = R.string.comment_order),
                                text = state.item.description ?: "",
                                enabled = state.enabled
                            )

                                    Text (
                                    text = stringResource(id = R.string.photo), style =
                                ArniTheme.typography.subhead.regular,
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
