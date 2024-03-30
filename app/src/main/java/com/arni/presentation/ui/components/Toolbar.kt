package com.arni.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.contentColorFor
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
    btnTitile: String = "",
    onBackPressed: (() -> Unit)? = null,
    actionsStart: @Composable () -> Unit = {},
    actionsEnd: @Composable RowScope.() -> Unit = {}
) {
    TopAppBarCenteredTitle(
        title = {
            Text(
                text = title,
                style = ArniTheme.typography.headline.semibold
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
        actions = actionsEnd
    )
}

@Composable
fun SelectorToolbarMainScreen(
    title: String = "",
    onNameClick: () -> Unit = {},
    onClickFilter: () -> Unit,
    onClickAddRequest: () -> Unit,
    onClickSearch: () -> Unit = {}
) {
    Column(
        modifier = Modifier.padding(bottom = 10.dp).shadow(
            elevation = 10.dp,
            spotColor = ArniTheme.colors.black_100,
        )
    ) {
        ToolbarMainScreen(
            title = {
                Column(
                    modifier = Modifier,
                    Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .clickable { onNameClick() }
                            .padding(horizontal = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = title,
                            style = ArniTheme.typography.headline.medium,
                            color = ArniTheme.colors.black_100
                        )
                        Icon(
                            modifier = Modifier.padding(start = 7.dp),
                            painter = painterResource(R.drawable.ic_down),
                            contentDescription = ""
                        )
                    }
                }
            },
            onClickFilter = { onClickFilter() },
           // onClickSearch = { onClickSearch() },
            onClickAddRequest = { onClickAddRequest() }
        )
    }
}

@Composable
private fun TopAppBarCenteredTitle(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
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
                    ProvideTextStyle(value = MaterialTheme.typography.h6) {
                        Box {
                            CompositionLocalProvider(
                                LocalContentAlpha provides ContentAlpha.high,
                                content = title
                            )
                        }
                    }
                }
            }
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Row(
                    Modifier
                        .fillMaxHeight()
                        .align(Alignment.CenterEnd),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    content = actions
                )
            }
        }
    }
}

@Composable
fun ToolbarMainScreen(
    title: @Composable () -> Unit,
    onClickFilter: () -> Unit = {},
    onClickSearch: () -> Unit = {},
    onClickAddRequest: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxWidth()
            .background(color = ArniTheme.colors.neutral_0)
            .padding(top = 12.dp, start = 20.dp, end = 20.dp, bottom = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box {
            CompositionLocalProvider(
                LocalContentAlpha provides ContentAlpha.high,
                content = title
            )
        }
        Row {
         /*   ButtonRoundedBase(
                modifier = Modifier
                    .padding(2.dp)
                    .size(36.dp),
                onClick = { onClickSearch() },
                contentColor = ArniTheme.colors.black_100,
                backgroundColor = ArniTheme.colors.neutral_100,
                iconId = R.drawable.ic_search
            )*/
            ButtonRoundedBase(
                modifier = Modifier
                    .padding(2.dp)
                    .size(36.dp),
                onClick = { onClickFilter() },
                contentColor = ArniTheme.colors.black_100,
                backgroundColor = ArniTheme.colors.neutral_100,
                iconId = R.drawable.ic_filter
            )

            ButtonRoundedBase(
                modifier = Modifier
                    .padding(2.dp)
                    .size(36.dp),
                iconSize = 32.dp,
                onClick = { onClickAddRequest() },
                contentColor = ArniTheme.colors.black_100,
                backgroundColor = ArniTheme.colors.neutral_100,
                iconId = R.drawable.ic_add
            )
        }
    }
}

@Composable
fun ButtonRoundedBase(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    contentColor: Color,
    backgroundColor: Color,
    iconId: Int,
    iconSize: Dp = 32.dp,
    enabled: Boolean = true
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        contentPadding = PaddingValues(all = 12.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            disabledBackgroundColor = ArniTheme.colors.neutral_100,
            disabledContentColor = ArniTheme.colors.neutral_100
        ),
        elevation = null,
        shape = CircleShape,
        border = null
    ) {


        Icon(
            modifier = Modifier.size(iconSize), // todo
            painter = painterResource(iconId),
            contentDescription = "",
            tint = contentColor
        )


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
            TextTitleToolbar(
                title = "Title",
                onBackPressed = {},
                actionsEnd = {
                    TextButton(onClick = {}) {
                        Text(color = ArniTheme.colors.black_100, text = "Cохранить")
                    }
                }
            )
            SelectorToolbarMainScreen(
                title = "Подразделение",
                onClickAddRequest = { /*TODO*/ },
                onClickSearch = {},
                onClickFilter = {}
            )
        }
    }
}
