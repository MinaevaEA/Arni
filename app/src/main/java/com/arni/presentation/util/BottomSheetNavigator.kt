package pro.midev.mec.util

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.SaveableStateHolder
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.stack.Stack
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// TODO: Выпилить после выхода voyager версии 1.0.0-rc05 https://github.com/adrielcafe/voyager

public typealias BottomSheetNavigatorContent = @Composable (bottomSheetNavigator: BottomSheetNavigator) -> Unit

public val LocalBottomSheetNavigator: ProvidableCompositionLocal<BottomSheetNavigator> =
    staticCompositionLocalOf { error("BottomSheetNavigator not initialized") }

@OptIn(ExperimentalLayoutApi::class)
@ExperimentalMaterialApi
@Composable
public fun BottomSheetNavigator(
    modifier: Modifier = Modifier,
    hideOnBackPress: Boolean = true,
    scrimColor: Color = ModalBottomSheetDefaults.scrimColor,
    sheetShape: Shape = MaterialTheme.shapes.large,
    sheetElevation: Dp = ModalBottomSheetDefaults.Elevation,
    sheetBackgroundColor: Color = MaterialTheme.colors.surface,
    sheetContentColor: Color = contentColorFor(sheetBackgroundColor),
    sheetContent: BottomSheetNavigatorContent = { CurrentScreen() },
    content: BottomSheetNavigatorContent
) {
    val currentFocusManager = LocalFocusManager.current
    var hideBottomSheet: (() -> Unit)? = null
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { state ->
            when (state) {
                ModalBottomSheetValue.Hidden -> {
                    hideBottomSheet?.invoke()
                    false
                }

                else -> true
            }
        },
        skipHalfExpanded = true // Все ради этой строчки
    )

    Navigator(HiddenBottomSheetScreen, onBackPressed = null) { navigator ->
        val bottomSheetNavigator = remember(navigator, sheetState, coroutineScope) {
            BottomSheetNavigator(navigator, sheetState, coroutineScope)
        }
        val isKeyboardOpen by rememberUpdatedState(WindowInsets.isImeVisible)

        hideBottomSheet = {
            if (isKeyboardOpen) {
                coroutineScope.launch { // костыль: сделан для того, чтобы не было бага с багованой клавиатурой при закрытии ботомшита
                    currentFocusManager.clearFocus()
                    delay(300)
                    bottomSheetNavigator.hide()
                }
            } else {
                bottomSheetNavigator.hide()
            }
        }

        CompositionLocalProvider(LocalBottomSheetNavigator provides bottomSheetNavigator) {
            ModalBottomSheetLayout(
                modifier = modifier,
                scrimColor = scrimColor,
                sheetState = sheetState,
                sheetShape = sheetShape,
                sheetElevation = sheetElevation,
                sheetBackgroundColor = sheetBackgroundColor,
                sheetContentColor = sheetContentColor,
                sheetContent = {
                    BottomSheetNavigatorBackHandler(bottomSheetNavigator, sheetState, hideOnBackPress)
                    sheetContent(bottomSheetNavigator)
                },
                content = {
                    content(bottomSheetNavigator)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
public class BottomSheetNavigator internal constructor(
    navigator: Navigator,
    private val sheetState: ModalBottomSheetState,
    private val coroutineScope: CoroutineScope,
    //val stateHolder: SaveableStateHolder = navigator.stateHolder
) : Stack<Screen> by navigator {

    public val isVisible: Boolean
        get() = sheetState.isVisible

    public fun show(screen: Screen) {
        coroutineScope.launch {
            replaceAll(screen)
            sheetState.show()
        }
    }

    public fun hide() {
        coroutineScope.launch {
            sheetState.hide()
            replaceAll(HiddenBottomSheetScreen)
        }
    }
}

private object HiddenBottomSheetScreen : Screen {

    @Composable
    override fun Content() {
        Spacer(modifier = Modifier.height(1.dp))
    }
}

@ExperimentalMaterialApi
@Composable
internal fun BottomSheetNavigatorBackHandler(
    navigator: BottomSheetNavigator,
    sheetState: ModalBottomSheetState,
    hideOnBackPress: Boolean
) {
    if (sheetState.isVisible) {
        BackHandler {
            if (navigator.pop().not() && hideOnBackPress) {
                navigator.hide()
            }
        }
    }
}
