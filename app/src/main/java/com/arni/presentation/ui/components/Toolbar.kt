package com.arni.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.arni.R
import pro.midev.mec.presentation.ui.style.ArniTheme

@Composable
fun TextTitleToolbar(
    title: String = "",
    onBackPressed: (() -> Unit)? = null,
    actionsStart: @Composable () -> Unit = {},
    countPages: String = ""
) {
    TopAppBarCenteredTitle(
        title = {
            Text(
                text = title,
                style = ArniTheme.typography.headline.bold
            )
        },
        navigationIcon = {
            onBackPressed?.let {
                IconButton(onClick = { it.invoke() }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_back),
                        contentDescription = "Назад"
                    )
                }
            } ?: run {
                actionsStart()
            }
        },
        backgroundColor = ArniTheme.colors.neutral_0,
        contentColor = ArniTheme.colors.black_100,
        elevation = 0.dp,
        pages = {
            Text(
                text = countPages,
                style = ArniTheme.typography.headline.bold
            )
        }
    )
}

@Composable
private fun TopAppBarCenteredTitle(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (() -> Unit)? = null,
    pages: @Composable RowScope.() -> Unit,
    backgroundColor: Color,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = AppBarDefaults.TopAppBarElevation
) {
    AppBar(
        backgroundColor,
        contentColor,
        elevation,
        AppBarDefaults.ContentPadding,
        RectangleShape,
        modifier
    ) {
        Box {
            Row {
                if (navigationIcon == null) {
                    Spacer(TitleInsetWithoutIcon)
                } else {
                    Row(TitleIconModifier, verticalAlignment = Alignment.CenterVertically) {
                        CompositionLocalProvider(
                            LocalContentAlpha provides ContentAlpha.high,
                            content = navigationIcon
                        )
                    }
                }

                Row(
                    Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .padding(
                            end = if (navigationIcon != null)
                                72.dp - AppBarHorizontalPadding
                            else
                                0.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    ProvideTextStyle(value = ArniTheme.typography.headline.bold) {
                        Box {
                            CompositionLocalProvider(
                                LocalContentAlpha provides ContentAlpha.high,
                                content = title
                            )
                        }
                    }
                }
            }
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                Row(
                    Modifier
                        .fillMaxHeight()
                        .align(Alignment.CenterEnd)
                        .padding(end = 16.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    content = pages
                )
            }
        }
    }
}

@Composable
private fun AppBar(
    backgroundColor: Color,
    contentColor: Color,
    elevation: Dp,
    contentPadding: PaddingValues,
    shape: Shape,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        color = backgroundColor,
        contentColor = contentColor,
        elevation = elevation,
        shape = shape,
        modifier = modifier
    ) {
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(contentPadding)
                    .height(AppBarHeight),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                content = content
            )
        }
    }
}

private val AppBarHeight = 56.dp
private val AppBarHorizontalPadding = 4.dp
private val TitleInsetWithoutIcon = Modifier.width(16.dp - AppBarHorizontalPadding)
private val TitleIconModifier = Modifier
    .fillMaxHeight()
    .width(72.dp - AppBarHorizontalPadding)

@Composable
@Preview()
private fun ToolbarsPreview() {
    ArniTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            TextTitleToolbar(
                title = "Title",
                onBackPressed = null
            )
            TextTitleToolbar(
                title = "Title",
                onBackPressed = {}
            )
        }
    }
}
