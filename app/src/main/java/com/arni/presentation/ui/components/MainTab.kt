package com.arni.presentation.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.arni.R
import com.arni.presentation.ui.screen.profile.ProfileScreen
import com.arni.presentation.ui.screen.request.general.GeneralRequestScreen

class GeneralTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val tabNavigator = LocalTabNavigator.current
            val isSelected = tabNavigator.current.key == this.key // хочу сделать проверку для разных иконок
            val title = "Заявки"
            val icon = painterResource(id = R.drawable.ic_basketball_outline)
            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(
           GeneralRequestScreen()
        )
    }

}

class ChatTab : Tab {

    override val options: TabOptions
        @Composable
        get() {

            val tabNavigator = LocalTabNavigator.current
            val isSelected = tabNavigator.current.key == this.key // хочу сделать проверку для разных иконок
            val title = "Чат"
            val icon = painterResource(id = R.drawable.ic_message_stroke)
            return remember {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(
            GeneralRequestScreen()
        )
    }
}


class ProfileTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val tabNavigator = LocalTabNavigator.current
            val isSelected = tabNavigator.current.key == this.key // хочу сделать проверку для разных иконок
            val title = "Профиль"
            val icon = painterResource(id = R.drawable.ic_profile_stroke)
            return remember {
                TabOptions(
                    index = 2u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(
            ProfileScreen()
        )
    }

}
