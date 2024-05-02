package com.arni.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import cafe.adriel.voyager.transitions.FadeTransition
import com.arni.R
import com.arni.data.local.keystorage.UserKeyStorage
import com.arni.events.EventType
import com.arni.events.Events
import com.arni.ext.launchIO
import com.arni.ext.withUI
import com.arni.presentation.ext.LocalGlobalNavigator
import com.arni.presentation.ui.screen.main.MainScreen
import com.arni.presentation.ui.screen.request.general.GeneralRequestScreen
import org.koin.core.component.KoinComponent
import com.arni.presentation.ui.screen.signIn.SignInScreen
import com.arni.remote.exceptions.ArniRemoteException
import com.arni.remote.exceptions.NoConnectivityException
import org.koin.core.component.inject
import retrofit2.HttpException

class EntryPointActivity : FragmentActivity(), KoinComponent {

    private val userKeyStorage: UserKeyStorage by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        listenToasts()
        start()
    }

    @SuppressLint("SuspiciousIndentation")
    private fun start() {
        lifecycleScope.launchIO {
            withUI {
                if (userKeyStorage.getToken().isNullOrEmpty())
                openScreen(SignInScreen())
                else
                openScreen(MainScreen())
            }
        }
    }

    @OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class)
    private fun openScreen(screen: AndroidScreen) {
        setContent {
            BottomSheetNavigator(
                sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomEnd = 0.dp, bottomStart = 0.dp)
            ) { bsNavigator ->
                Navigator(screen = screen) { navigator ->
                    CompositionLocalProvider(LocalGlobalNavigator provides navigator) {
                        FadeTransition(navigator) {
                            if (LocalSavedStateRegistryOwner.current.lifecycle.currentState != Lifecycle.State.DESTROYED) {
                                it.Content()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun listenToasts() {
        lifecycleScope.launchIO {
            Events.subscribe<EventType.ShowErrorToast> { toastData ->
                withUI { showErrorToast(toastData.ex) }
            }
        }
    }

    private fun showErrorToast(ex: Exception) {
        val message: String = when (ex) {
            is NoConnectivityException -> getString(R.string.error_no_connectivity)
            is ArniRemoteException -> ex.remoteMessage
            is HttpException -> when (ex.code()) {
                401 -> getString(R.string.error_unauthorized)
                500 -> getString(R.string.error_server)
                else -> getString(R.string.error_undefined)
            }

            else -> getString(R.string.update)
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
