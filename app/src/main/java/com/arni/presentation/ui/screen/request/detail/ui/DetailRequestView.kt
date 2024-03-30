package com.arni.presentation.ui.screen.request.detail.ui

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arni.R
import com.arni.presentation.enum.StatusRoleHuman
import com.arni.presentation.ui.components.AddFilesBS
import com.arni.presentation.ui.components.TextFieldInput
import com.arni.presentation.ui.components.TextFieldSelector
import com.arni.presentation.ui.components.TextTitleToolbar
import com.arni.presentation.ui.screen.request.create.ui.PickFileOption
import com.arni.presentation.util.ComposeFileProvider
import kotlinx.coroutines.launch
import pro.midev.mec.presentation.ui.style.ArniTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailRequestView(
    state: DetailRequestState, eventConsumer: (DetailRequestEvent) -> Unit
) {
    var showSelectMediaOptionBs = remember {
        mutableStateOf(false)
    }
    val listState = rememberLazyListState()
    val context = LocalContext.current
    var cameraImageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val uri = ComposeFileProvider.getImageUri(context)
    val pickPhotoLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(6)) {
            it.forEach {
                context.contentResolver.takePersistableUriPermission(it, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            /* eventConsumer(
                 DetailRequestEvent.OnFileChosen(
                     it.take(6 - state.item.photos.size).map { uri ->
                         uri.toString()
                     }.toList()
                 )
             )*/
        }
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success)
                eventConsumer.invoke(DetailRequestEvent.OnFileChosen(listOf(cameraImageUri.toString())))
        }
    )
    val scope = rememberCoroutineScope()
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            pickPhotoLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }

    val requestCameraPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            cameraImageUri = uri
            cameraLauncher.launch(uri)
        }
    }
    val bsSelectMediaOptionState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    LaunchedEffect(state.currentPickFileOption) {
        when (state.currentPickFileOption) {
            PickFileOption.CAMERA -> {
                eventConsumer(DetailRequestEvent.ChangeFilePickerOption(PickFileOption.NONE))
                if (Build.VERSION.SDK_INT < 32) {
                    requestCameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                } else {
                    cameraImageUri = uri
                    cameraLauncher.launch(uri)
                }
            }

            PickFileOption.GALLERY -> {
                eventConsumer(DetailRequestEvent.ChangeFilePickerOption(PickFileOption.NONE))
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
                    pickPhotoLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                else
                    requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }

            else -> {}
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ArniTheme.colors.neutral_0)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        if (showSelectMediaOptionBs.value) {
            ModalBottomSheet(
                dragHandle = null,
                modifier = Modifier
                    .fillMaxWidth(),
                containerColor = ArniTheme.colors.white_100,
                sheetState = bsSelectMediaOptionState,
                onDismissRequest = {
                    showSelectMediaOptionBs.value = false
                },
                shape = RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                )
            ) {
                AddFilesBS(
                    onMakePhotoAction = {
                        scope.launch {
                            bsSelectMediaOptionState.hide()
                            eventConsumer.invoke(DetailRequestEvent.ChangeFilePickerOption(PickFileOption.CAMERA))
                        }.invokeOnCompletion {
                            showSelectMediaOptionBs.value = false
                        }
                    },
                    onOpenGalleryAction = {
                        scope.launch {
                            bsSelectMediaOptionState.hide()
                            eventConsumer.invoke(DetailRequestEvent.ChangeFilePickerOption(PickFileOption.GALLERY))
                        }.invokeOnCompletion {
                            showSelectMediaOptionBs.value = false
                        }
                    },
                    onOpenFileAction = { }
                )
            }
        }

        TextTitleToolbar(
            title = "",
            onBackPressed = { eventConsumer(DetailRequestEvent.onClickBackList) },
            actionsEnd = {
                IconButton(
                    enabled = state.isEditRequest && state.isEnabledButton,
                    onClick = { eventConsumer(DetailRequestEvent.onClickToolbarButton(!state.enabled)) }
                ) {
                    Icon(
                        painter = if (!state.enabled) painterResource(R.drawable.ic_chat_pen) else painterResource(R.drawable.ic_check_mark),
                        contentDescription = ""
                    )
                }
            })
        LazyColumn(modifier = Modifier) {
            item {
                Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    TextFieldSelector(
                        label = stringResource(id = R.string.status_order),
                        onClick = { eventConsumer(DetailRequestEvent.onClickSelectStatus) },
                        text = when (state.item.statusRequest?.guid) {
                            /*       StatusRequests.parse(StatusRequests.WORK) -> "Рабочая"
                                   StatusRequests.parse(StatusRequests.DRAFT) -> "Черновик"
                                   StatusRequests.parse(StatusRequests.COMPLETED) -> "Завершена"*/
                            else -> {
                                ""
                            }
                        },
                        enabled = if (state.human.role != StatusRoleHuman.INITIAL) state.enabled else false
                    )
                    TextFieldSelector(
                        label = stringResource(id = R.string.date_order),
                        onClick = { eventConsumer(DetailRequestEvent.onClickSelectorDate) },
                        text = state.item.date ?: "",
                        enabled = if (state.human.role == StatusRoleHuman.EXECUTOR || state.human.role == StatusRoleHuman.EXECUTOR_INITIAL) false else state.enabled
                    )

                    TextFieldSelector(
                        label = stringResource(id = R.string.time_order),
                        onClick = { eventConsumer(DetailRequestEvent.onClickSelectorTime) },
                        text = state.item.date ?: "",
                        enabled = if (state.human.role == StatusRoleHuman.EXECUTOR || state.human.role == StatusRoleHuman.EXECUTOR_INITIAL) false else state.enabled
                    )
                    TextFieldSelector(
                        label = stringResource(id = R.string.local_order),
                        onClick = { eventConsumer(DetailRequestEvent.onClickSelectsubDivision) },
                        text = state.human.subdivision ?: "",
                        enabled =
                        if (state.human.role == StatusRoleHuman.EXECUTOR || state.human.role == StatusRoleHuman.INITIAL || state.human.role == StatusRoleHuman.EXECUTOR_INITIAL) false else state.enabled
                    )
                    TextFieldSelector(
                        label = stringResource(id = R.string.from_local),
                        onClick = { eventConsumer(DetailRequestEvent.onClickSelectDepartament) },
                        text = state.item.departamentFrom.name ?: "",
                        enabled = if (state.human.role == StatusRoleHuman.EXECUTOR || state.human.role == StatusRoleHuman.EXECUTOR_INITIAL) false else state.enabled
                    )

                    TextFieldSelector(
                        label = stringResource(id = R.string.to_local),
                        onClick = { eventConsumer(DetailRequestEvent.onClickSelectDepartament) },
                        text = state.item.departamentTo.name ?: "",
                        enabled = if (state.human.role == StatusRoleHuman.EXECUTOR || state.human.role == StatusRoleHuman.EXECUTOR_INITIAL) false else state.enabled
                    )
                    TextFieldSelector(
                        label = stringResource(id = R.string.begin_date),
                        onClick = { eventConsumer(DetailRequestEvent.onClickSelectorTime) },
                        text = state.item.startDate ?: "",
                        enabled = if (state.human.role != StatusRoleHuman.INITIAL) state.enabled else false
                    )
                    TextFieldSelector(
                        label = stringResource(id = R.string.end_date),
                        onClick = { eventConsumer(DetailRequestEvent.onClickSelectorTime) },
                        text = state.item.endDate ?: "",
                        enabled = if (state.human.role != StatusRoleHuman.INITIAL) state.enabled else false
                    )

                    TextFieldInput(
                        label = stringResource(id = R.string.name_dispatcher),
                        text = state.item.nameDispatcher ?: "",
                        enabled = if (state.human.role != StatusRoleHuman.INITIAL) state.enabled else false
                    )
                    TextFieldSelector(
                        label = stringResource(id = R.string.draft_order),
                        onClick = { eventConsumer(DetailRequestEvent.onClickSelectUrgently) },
                        text = state.item.urgency?.name ?: "",
                        enabled =
                        if (state.human.role == StatusRoleHuman.EXECUTOR || state.human.role == StatusRoleHuman.EXECUTOR_INITIAL) false else state.enabled
                    )
                    TextFieldInput(
                        label = stringResource(id = R.string.name_patient_order),
                        text = /*state.item.patients ?:*/ "",
                        enabled = state.enabled
                    )

                    TextFieldSelector(
                        label = stringResource(id = R.string.checking_order),
                        onClick = { eventConsumer(DetailRequestEvent.onClickSelectExecutor) },
                        text = /*state.item.executors?. ?:*/ "",
                        enabled = if (state.human.role == StatusRoleHuman.EXECUTOR || state.human.role == StatusRoleHuman.INITIAL) false else state.enabled
                    )
                    TextFieldSelector(
                        label = stringResource(id = R.string.status_patient),
                        onClick = { eventConsumer(DetailRequestEvent.onClickSelectStatusPatient) },
                        text = state.item.statusPatient?.name ?: "",
                        enabled = state.enabled
                    )
                    TextFieldInput(
                        label = stringResource(id = R.string.comment_order),
                        text = state.item.description ?: "",
                        enabled = state.enabled
                    )

                    Text(
                        text = stringResource(id = R.string.photo), style =
                        ArniTheme.typography.subhead.regular,
                        color = ArniTheme.colors.neutral_300,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    /*  PhotoLine(
                          addPhotoAction = {
                              showSelectMediaOptionBs.value = true
                          },
                          deletePhotoAction = {},
                          photos = state.item.photos,
                      )*/
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
