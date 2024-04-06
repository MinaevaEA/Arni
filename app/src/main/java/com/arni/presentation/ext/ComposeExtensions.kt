package com.arni.presentation.ext

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalFocusManager

fun Modifier.condition(
    condition: Boolean,
    modifier: Modifier.() -> Modifier
) = this.let {
    if (condition) it.modifier() else it
}

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}

@Immutable
class LaunchEffectTrigger()

@Immutable
class HideBottomMenuTrigger(
    val isVisible: Boolean
)

@OptIn(ExperimentalLayoutApi::class)
fun Modifier.clearFocusOnKeyboardDismiss(hasFocus: MutableState<Boolean>): Modifier = composed {
    var keyboardAppearedSinceLastFocused by remember { mutableStateOf(false) }
    if (hasFocus.value) {
        val imeIsVisible = WindowInsets.isImeVisible
        val focusManager = LocalFocusManager.current
        LaunchedEffect(imeIsVisible) {
            if (imeIsVisible) {
                keyboardAppearedSinceLastFocused = true
            } else if (keyboardAppearedSinceLastFocused) {
                focusManager.clearFocus()
            }
        }
    }
    onFocusEvent {
        if (hasFocus.value != it.isFocused) {
            hasFocus.value = it.isFocused
            if (hasFocus.value) {
                keyboardAppearedSinceLastFocused = false
            }
        }
    }
}

@Composable
fun AnimatePath(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Transparent,
    blendMode: BlendMode = BlendMode.SrcOver,
    points: List<Offset>,
    duration: Int,
    style: DrawStyle,
    color: Color,
) {
    val path = remember {
        Path().apply {
            moveTo(points.first().x, points.first().y)
            points.forEach { point ->
                this.lineTo(point.x, point.y)
            }
        }
    }

    val drawPathAnimation = remember {
        Animatable(0f)
    }
    val pathMeasure = remember {
        PathMeasure()
    }
    LaunchedEffect(key1 = Unit, block = {
        drawPathAnimation.animateTo(
            1f,
            animationSpec = tween(duration)
        )
    })
    val animatedPath by remember {
        derivedStateOf {
            val newPath = Path()
            pathMeasure.setPath(path, false)
            pathMeasure.getSegment(
                0f,
                drawPathAnimation.value * pathMeasure.length, newPath
            )
            newPath
        }
    }

    Canvas(
        modifier = modifier
            .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
    ) {

        drawRect(
            color = backgroundColor,
            topLeft = Offset.Zero,
            size = this.size
        )

        drawPath(
            color = color,
            path = animatedPath,
            style = style,
            blendMode = blendMode
        )
    }
}
val LazyListState.isLastItemVisible: Boolean
    get() = layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1

val LazyListState.isFirstItemVisible: Boolean
    get() = firstVisibleItemIndex == 0
