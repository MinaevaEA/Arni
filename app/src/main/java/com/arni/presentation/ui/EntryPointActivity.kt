package com.arni.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import com.arni.R
import com.arni.data.base.DataStatus
import com.arni.data.local.keystorage.UserKeyStorage
import com.arni.ext.launchIO
import com.arni.ext.withUI
import org.koin.core.component.KoinComponent
import com.arni.presentation.ui.screen.signIn.SignInScreen
import com.arni.remote.exceptions.ArniRemoteException
import com.arni.remote.exceptions.NoConnectivityException
import org.koin.core.component.inject
import retrofit2.HttpException

class EntryPointActivity : FragmentActivity(), KoinComponent {

    private val userKeyStorage: UserKeyStorage by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        start()
    }

    private fun start() {
        lifecycleScope.launchIO {
            openScreen(SignInScreen())
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    private fun openScreen(screen: AndroidScreen) {
        setContent {
            Navigator(screen = screen) { navigator ->
                FadeTransition(navigator) {
                    if (LocalSavedStateRegistryOwner.current.lifecycle.currentState != Lifecycle.State.DESTROYED) {
                        it.Content()
                    }
                }
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

                else -> getString(R.string.error_undefined)
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
