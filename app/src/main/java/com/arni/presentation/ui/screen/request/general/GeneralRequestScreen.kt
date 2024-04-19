package com.arni.presentation.ui.screen.request.general

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.arni.presentation.ext.LocalGlobalNavigator
import com.arni.presentation.ui.screen.filter.FilterScreen
import com.arni.presentation.ui.screen.request.create.CreateRequestScreen
import com.arni.presentation.ui.screen.request.detail.DetailRequestScreen
import com.arni.presentation.ui.screen.request.general.ui.GeneralRequestAction
import com.arni.presentation.ui.screen.request.general.ui.GeneralRequestView
import com.arni.presentation.ui.screen.request.general.ui.GeneralRequestViewModel
import com.arni.presentation.ui.screen.request.select_division_general.SelectDivisionScreen
import org.koin.androidx.compose.koinViewModel
import pro.midev.mec.presentation.ui.style.ArniTheme

class GeneralRequestScreen : AndroidScreen() {

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    override fun Content() {
        GeneralCheckingScreen(viewModel = koinViewModel())
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun GeneralCheckingScreen(
    viewModel: GeneralRequestViewModel
) {
    val navigator = LocalGlobalNavigator.current
    val bottomSheetNavigator = LocalBottomSheetNavigator.current
    val state by viewModel.viewStates.collectAsStateWithLifecycle()
    val action by viewModel.viewActions.collectAsStateWithLifecycle(initialValue = null)

    LaunchedEffect(action) {
        when (val act = action) {
            is GeneralRequestAction.OpenScreenDetailInfo ->
                navigator.push(DetailRequestScreen(act.listId, act.item, act.user, act.dictionaryHuman, act.division))
            is GeneralRequestAction.OpenScreenAddRequest -> navigator.push(CreateRequestScreen())
            GeneralRequestAction.ExitScreen -> navigator.pop()
            is GeneralRequestAction.OpenScreenFilter -> bottomSheetNavigator.show(FilterScreen())
            is GeneralRequestAction.OpenListDivision -> bottomSheetNavigator
                .show(SelectDivisionScreen(act.listDivision, act.listID))

            else -> {}
        }
    }

    ArniTheme() {
        GeneralRequestView(
            state = state,
            eventConsumer = viewModel::obtainEvent
        )
    }
}

