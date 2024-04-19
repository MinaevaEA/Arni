package com.arni.presentation.ui.screen.select_departament

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.arni.presentation.model.human.DepartmentHuman
import com.arni.presentation.ui.screen.select_departament.ui.SelectDepartmentAction
import com.arni.presentation.ui.screen.select_departament.ui.SelectDepartmentView
import com.arni.presentation.ui.screen.select_departament.ui.SelectDepartmentViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import pro.midev.mec.presentation.ui.style.ArniTheme

class SelectDepartmentScreen(
    private val departments: List<DepartmentHuman>,
    private val onSelect: (DepartmentHuman) -> Unit,
) : Screen {

    @Composable
    override fun Content() {
        val bottomSheetNavigator = LocalBottomSheetNavigator.current // TODO refactoring?

        ArniTheme {
            SelectDepartmentView(
                departments = departments,
                onSelect = { onSelect(it); bottomSheetNavigator.hide() },
                onExit = bottomSheetNavigator::hide
            )
        }
    }
}
