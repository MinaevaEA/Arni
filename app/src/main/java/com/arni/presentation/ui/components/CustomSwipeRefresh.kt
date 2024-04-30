/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.arni.presentation.ui.components

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.MutatorMutex
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalElevationOverlay
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
import androidx.compose.material.pullrefresh.PullRefreshDefaults
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.platform.inspectable
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.arni.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

/**
 * The default indicator for Compose pull-to-refresh, based on Android's SwipeRefreshLayout.
 *
 * @sample androidx.compose.material.samples.PullRefreshSample
 *
 * @param refreshing A boolean representing whether a refresh is occurring.
 * @param state The [PullRefreshState] which controls where and how the indicator will be drawn.
 * @param modifier Modifiers for the indicator.
 * @param backgroundColor The color of the indicator's background.
 * @param contentColor The color of the indicator's arc and arrow.
 * @param scale A boolean controlling whether the indicator's size scales with pull progress or not.
 */
@Composable
@ExperimentalMaterialApi
// TODO(b/244423199): Consider whether the state parameter should be replaced with lambdas to
//  enable people to use this indicator with custom pull-to-refresh components.
fun CustomPullRefreshIndicator(
    refreshing: Boolean,
    state: PullRefreshState,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = contentColorFor(backgroundColor),
    scale: Boolean = false
) {
    val showElevation by remember(refreshing, state) {
        derivedStateOf { refreshing || state.position > 0.5f }
    }

    val compositionViolet by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(resId = R.raw.violet_green)
    )

    // Apply an elevation overlay if needed. Note that we aren't using Surface here, as we do not
    // want its input-blocking behaviour, since the indicator is typically displayed above other
    // (possibly) interactive content.
    val elevationOverlay = LocalElevationOverlay.current
    val color = elevationOverlay?.apply(color = backgroundColor, elevation = Elevation)
        ?: backgroundColor

    Box(
        modifier = modifier
            .size(IndicatorSize)
            .pullRefreshIndicatorTransformCustom(state, scale)
            .shadow(if (showElevation) Elevation else 0.dp, SpinnerShape, clip = true)
            .background(color = color, shape = SpinnerShape)
    ) {
        Crossfade(
            targetState = refreshing,
            animationSpec = tween(durationMillis = CrossfadeDurationMs), label = ""
        ) { refreshing ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                val spinnerSize = (ArcRadius + StrokeWidth).times(2)

                if (refreshing) {
                    LottieAnimation(
                        modifier = Modifier.fillMaxSize(),
                        composition = compositionViolet,
                        contentScale = ContentScale.Crop,
                        iterations = LottieConstants.IterateForever
                    )


                } else {
                    CircularArrowIndicator(state, contentColor, Modifier.size(spinnerSize))
                }
            }
        }
    }
}

/**
 * Modifier.size MUST be specified.
 */
@Composable
@ExperimentalMaterialApi
private fun CircularArrowIndicator(
    state: PullRefreshState,
    color: Color,
    modifier: Modifier,
) {
    val path = remember { Path().apply { fillType = PathFillType.EvenOdd } }

    val targetAlpha by remember(state) {
        derivedStateOf {
            if (state.progress >= 1f) MaxAlpha else MinAlpha
        }
    }

    val alphaState = animateFloatAsState(targetValue = targetAlpha, animationSpec = AlphaTween)

    // Empty semantics for tests
    Canvas(modifier.semantics {}) {
        val values = ArrowValues(state.progress)
        val alpha = alphaState.value

        rotate(degrees = values.rotation) {
            val arcRadius = ArcRadius.toPx() + StrokeWidth.toPx() / 2f
            val arcBounds = Rect(
                size.center.x - arcRadius,
                size.center.y - arcRadius,
                size.center.x + arcRadius,
                size.center.y + arcRadius
            )
            drawArc(
                color = color,
                alpha = alpha,
                startAngle = values.startAngle,
                sweepAngle = values.endAngle - values.startAngle,
                useCenter = false,
                topLeft = arcBounds.topLeft,
                size = arcBounds.size,
                style = Stroke(
                    width = StrokeWidth.toPx(),
                    cap = StrokeCap.Square
                )
            )
            drawArrow(path, arcBounds, color, alpha, values)
        }
    }
}

@Immutable
private class ArrowValues(
    val rotation: Float,
    val startAngle: Float,
    val endAngle: Float,
    val scale: Float
)

private fun ArrowValues(progress: Float): ArrowValues {
    // Discard first 40% of progress. Scale remaining progress to full range between 0 and 100%.
    val adjustedPercent = max(min(1f, progress) - 0.4f, 0f) * 5 / 3
    // How far beyond the threshold pull has gone, as a percentage of the threshold.
    val overshootPercent = abs(progress) - 1.0f
    // Limit the overshoot to 200%. Linear between 0 and 200.
    val linearTension = overshootPercent.coerceIn(0f, 2f)
    // Non-linear tension. Increases with linearTension, but at a decreasing rate.
    val tensionPercent = linearTension - linearTension.pow(2) / 4

    // Calculations based on SwipeRefreshLayout specification.
    val endTrim = adjustedPercent * MaxProgressArc
    val rotation = (-0.25f + 0.4f * adjustedPercent + tensionPercent) * 0.5f
    val startAngle = rotation * 360
    val endAngle = (rotation + endTrim) * 360
    val scale = min(1f, adjustedPercent)

    return ArrowValues(rotation, startAngle, endAngle, scale)
}

private fun DrawScope.drawArrow(
    arrow: Path,
    bounds: Rect,
    color: Color,
    alpha: Float,
    values: ArrowValues
) {
    arrow.reset()
    arrow.moveTo(0f, 0f) // Move to left corner
    arrow.lineTo(x = ArrowWidth.toPx() * values.scale, y = 0f) // Line to right corner

    // Line to tip of arrow
    arrow.lineTo(
        x = ArrowWidth.toPx() * values.scale / 2,
        y = ArrowHeight.toPx() * values.scale
    )

    val radius = min(bounds.width, bounds.height) / 2f
    val inset = ArrowWidth.toPx() * values.scale / 2f
    arrow.translate(
        Offset(
            x = radius + bounds.center.x - inset,
            y = bounds.center.y + StrokeWidth.toPx() / 2f
        )
    )
    arrow.close()
    rotate(degrees = values.endAngle) {
        drawPath(path = arrow, color = color, alpha = alpha)
    }
}

private const val CrossfadeDurationMs = 100
private const val MaxProgressArc = 0.8f

private val IndicatorSize = 40.dp
private val SpinnerShape = CircleShape
private val ArcRadius = 7.5.dp
private val StrokeWidth = 2.5.dp
private val ArrowWidth = 10.dp
private val ArrowHeight = 5.dp
private val Elevation = 6.dp

// Values taken from SwipeRefreshLayout
private const val MinAlpha = 0.3f
private const val MaxAlpha = 1f
private val AlphaTween = tween<Float>(300, easing = LinearEasing)

@ExperimentalMaterialApi
class PullRefreshState internal constructor(
    private val animationScope: CoroutineScope,
    private val onRefreshState: State<() -> Unit>,
    refreshingOffset: Float,
    threshold: Float
) {
    /**
     * A float representing how far the user has pulled as a percentage of the refreshThreshold.
     *
     * If the component has not been pulled at all, progress is zero. If the pull has reached
     * halfway to the threshold, progress is 0.5f. A value greater than 1 indicates that pull has
     * gone beyond the refreshThreshold - e.g. a value of 2f indicates that the user has pulled to
     * two times the refreshThreshold.
     */
    val progress get() = adjustedDistancePulled / threshold

    internal val refreshing get() = _refreshing
    internal val position get() = _position
    internal val threshold get() = _threshold

    private val adjustedDistancePulled by derivedStateOf { distancePulled * DragMultiplier }

    private var _refreshing by mutableStateOf(false)
    private var _position by mutableFloatStateOf(0f)
    private var distancePulled by mutableFloatStateOf(0f)
    private var _threshold by mutableFloatStateOf(threshold)
    private var _refreshingOffset by mutableFloatStateOf(refreshingOffset)

    internal fun onPull(pullDelta: Float): Float {
        if (_refreshing) return 0f // Already refreshing, do nothing.

        val newOffset = (distancePulled + pullDelta).coerceAtLeast(0f)
        val dragConsumed = newOffset - distancePulled
        distancePulled = newOffset
        _position = calculateIndicatorPosition()
        return dragConsumed
    }

    internal fun onRelease(velocity: Float): Float {
        if (refreshing) return 0f // Already refreshing, do nothing

        if (adjustedDistancePulled > threshold) {
            onRefreshState.value()
        }
        animateIndicatorTo(0f)
        val consumed = when {
            // We are flinging without having dragged the pull refresh (for example a fling inside
            // a list) - don't consume
            distancePulled == 0f -> 0f
            // If the velocity is negative, the fling is upwards, and we don't want to prevent the
            // the list from scrolling
            velocity < 0f -> 0f
            // We are showing the indicator, and the fling is downwards - consume everything
            else -> velocity
        }
        distancePulled = 0f
        return consumed
    }

    internal fun setRefreshing(refreshing: Boolean) {
        if (_refreshing != refreshing) {
            _refreshing = refreshing
            distancePulled = 0f
            animateIndicatorTo(if (refreshing) _refreshingOffset else 0f)
        }
    }

    internal fun setThreshold(threshold: Float) {
        _threshold = threshold
    }

    internal fun setRefreshingOffset(refreshingOffset: Float) {
        if (_refreshingOffset != refreshingOffset) {
            _refreshingOffset = refreshingOffset
            if (refreshing) animateIndicatorTo(refreshingOffset)
        }
    }

    // Make sure to cancel any existing animations when we launch a new one. We use this instead of
    // Animatable as calling snapTo() on every drag delta has a one frame delay, and some extra
    // overhead of running through the animation pipeline instead of directly mutating the state.
    private val mutatorMutex = MutatorMutex()

    private fun animateIndicatorTo(offset: Float) = animationScope.launch {
        mutatorMutex.mutate {
            animate(initialValue = _position, targetValue = offset) { value, _ ->
                _position = value
            }
        }
    }

    private fun calculateIndicatorPosition(): Float = when {
        // If drag hasn't gone past the threshold, the position is the adjustedDistancePulled.
        adjustedDistancePulled <= threshold -> adjustedDistancePulled
        else -> {
            // How far beyond the threshold pull has gone, as a percentage of the threshold.
            val overshootPercent = abs(progress) - 1.0f
            // Limit the overshoot to 200%. Linear between 0 and 200.
            val linearTension = overshootPercent.coerceIn(0f, 2f)
            // Non-linear tension. Increases with linearTension, but at a decreasing rate.
            val tensionPercent = linearTension - linearTension.pow(2) / 4
            // The additional offset beyond the threshold.
            val extraOffset = threshold * tensionPercent
            threshold + extraOffset
        }
    }
}

@ExperimentalMaterialApi
fun Modifier.pullRefreshCustom(
    state: PullRefreshState,
    enabled: Boolean = true
) = inspectable(inspectorInfo = debugInspectorInfo {
    name = "pullRefresh"
    properties["state"] = state
    properties["enabled"] = enabled
}) {
    Modifier.pullRefresh(state::onPull, state::onRelease, enabled)
}

private class PullRefreshNestedScrollConnection(
    private val onPull: (pullDelta: Float) -> Float,
    private val onRelease: suspend (flingVelocity: Float) -> Float,
    private val enabled: Boolean
) : NestedScrollConnection {

    override fun onPreScroll(
        available: Offset,
        source: NestedScrollSource
    ): Offset = when {
        !enabled -> Offset.Zero
        source == NestedScrollSource.Drag && available.y < 0 -> Offset(0f, onPull(available.y)) // Swiping up
        else -> Offset.Zero
    }

    override fun onPostScroll(
        consumed: Offset,
        available: Offset,
        source: NestedScrollSource
    ): Offset = when {
        !enabled -> Offset.Zero
        source == NestedScrollSource.Drag && available.y > 0 -> Offset(0f, onPull(available.y)) // Pulling down
        else -> Offset.Zero
    }

    override suspend fun onPreFling(available: Velocity): Velocity {
        return Velocity(0f, onRelease(available.y))
    }
}


@ExperimentalMaterialApi
fun Modifier.pullRefresh(
    onPull: (pullDelta: Float) -> Float,
    onRelease: suspend (flingVelocity: Float) -> Float,
    enabled: Boolean = true
) = inspectable(inspectorInfo = debugInspectorInfo {
    name = "pullRefresh"
    properties["onPull"] = onPull
    properties["onRelease"] = onRelease
    properties["enabled"] = enabled
}) {
    Modifier.nestedScroll(PullRefreshNestedScrollConnection(onPull, onRelease, enabled))
}

@ExperimentalMaterialApi
// TODO: Consider whether the state parameter should be replaced with lambdas.
fun Modifier.pullRefreshIndicatorTransformCustom(
    state: PullRefreshState,
    scale: Boolean = false,
) = inspectable(inspectorInfo = debugInspectorInfo {
    name = "pullRefreshIndicatorTransform"
    properties["state"] = state
    properties["scale"] = scale
}) {
    Modifier
        // Essentially we only want to clip the at the top, so the indicator will not appear when
        // the position is 0. It is preferable to clip the indicator as opposed to the layout that
        // contains the indicator, as this would also end up clipping shadows drawn by items in a
        // list for example - so we leave the clipping to the scrolling container. We use MAX_VALUE
        // for the other dimensions to allow for more room for elevation / arbitrary indicators - we
        // only ever really want to clip at the top edge.
        .drawWithContent {
            clipRect(
                top = 0f,
                left = -Float.MAX_VALUE,
                right = Float.MAX_VALUE,
                bottom = Float.MAX_VALUE
            ) {
                this@drawWithContent.drawContent()
            }
        }
        .graphicsLayer {
            translationY = state.position - size.height

            if (scale && !state.refreshing) {
                val scaleFraction = LinearOutSlowInEasing
                    .transform(state.position / state.threshold)
                    .coerceIn(0f, 1f)
                scaleX = scaleFraction
                scaleY = scaleFraction
            }
        }
}

private const val DragMultiplier = 0.5f

@Composable
@ExperimentalMaterialApi
fun rememberCustomPullRefreshState(
    refreshing: Boolean,
    onRefresh: () -> Unit,
    refreshThreshold: Dp = PullRefreshDefaults.RefreshThreshold,
    refreshingOffset: Dp = PullRefreshDefaults.RefreshingOffset,
): PullRefreshState {
    require(refreshThreshold > 0.dp) { "The refresh trigger must be greater than zero!" }

    val scope = rememberCoroutineScope()
    val onRefreshState = rememberUpdatedState(onRefresh)
    val thresholdPx: Float
    val refreshingOffsetPx: Float

    with(LocalDensity.current) {
        thresholdPx = refreshThreshold.toPx()
        refreshingOffsetPx = refreshingOffset.toPx()
    }

    val state = remember(scope) {
        PullRefreshState(scope, onRefreshState, refreshingOffsetPx, thresholdPx)
    }

    SideEffect {
        state.setRefreshing(refreshing)
        state.setThreshold(thresholdPx)
        state.setRefreshingOffset(refreshingOffsetPx)
    }

    return state
}
