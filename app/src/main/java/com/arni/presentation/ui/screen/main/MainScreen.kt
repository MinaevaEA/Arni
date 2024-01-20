package com.arni.presentation.ui.screen.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.arni.presentation.ui.components.ChatTab
import com.arni.presentation.ui.components.GeneralTab
import com.arni.presentation.ui.components.ProfileTab
import org.koin.androidx.compose.getViewModel
import pro.midev.mec.presentation.ui.style.ArniTheme

class MainScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        ArniTheme {
            val viewModel: MainScreenViewModel = getViewModel()
            val state by viewModel.viewStates.collectAsStateWithLifecycle()

            var toggled = remember {
                mutableStateOf(false)
            }

            LaunchedEffect(true) {
                viewModel.obtainEvent(MainScreenEvent.OnCreate)
            }

            LaunchedEffect(state.changeVisibilityBottomMenuTrigger) {
                state.changeVisibilityBottomMenuTrigger?.let {
                    toggled.value = it.isVisible
                }
            }

            TabNavigator(tab = GeneralTab()) { tabNavigator ->

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .navigationBarsPadding()
                ) {

                    Box(modifier = Modifier.fillMaxSize()) {
                        CurrentTab()
                    }

                    AnimatedVisibility(
                        modifier = Modifier.align(Alignment.BottomCenter),
                        visible = !toggled.value,
                        enter = slideInVertically(spring(stiffness = Spring.StiffnessMediumLow), initialOffsetY = {
                            it
                        }),
                        exit = slideOutVertically(spring(stiffness = Spring.StiffnessMediumLow), targetOffsetY = {
                            it
                        })
                    ) {

                        BottomNavigation(
                            modifier = Modifier
                                .height(80.dp)
                                .align(Alignment.BottomCenter),
                            elevation = 0.dp,
                            backgroundColor = ArniTheme.colors.neutral_0
                        ) {
                            TabNavigationItem(GeneralTab())
                            TabNavigationItem(ChatTab())
                            TabNavigationItem(ProfileTab())
                        }
                    }

                }
            }
        }
    }


    @Composable
    private fun RowScope.TabNavigationItem(tab: Tab) {
        val tabNavigator = LocalTabNavigator.current
        val isSelected = tabNavigator.current.key == tab.key
        BottomNavigationItem(
            selected = isSelected,
            onClick = { tabNavigator.current = tab },
            selectedContentColor = ArniTheme.colors.white_100,
            label = {
                Text(
                    maxLines = 1,
                    text = tab.options.title,
                    style = ArniTheme.typography.subhead.medium,
                    color = if (isSelected)
                        ArniTheme.colors.black_100
                    else
                        ArniTheme.colors.black_100.copy(alpha = 0.5f)
                )
            },
            icon = {
                Box() {
                    tab.options.icon?.let {
                        Icon(
                            modifier = Modifier
                                .zIndex(1F)
                                .clip(shape = RoundedCornerShape(10.dp))
                                .background(color = if (isSelected) ArniTheme.colors.black_100.copy(alpha = 0.1f) else ArniTheme.colors.white_100)
                                .padding(horizontal = 20.dp, vertical = 6.dp),
                            painter = it,
                            contentDescription = tab.options.title,
                            tint = if (isSelected)
                                ArniTheme.colors.black_100
                            else
                                ArniTheme.colors.black_100.copy(alpha = 0.5f)
                        )
                    }
                }

            }
        )
    }
}
