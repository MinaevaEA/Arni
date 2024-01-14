package com.arni.ext

import kotlinx.coroutines.*
import timber.log.Timber

fun CoroutineScope.launchUI(callback: suspend () -> Unit) = launch(Dispatchers.Main) { callback() }

fun CoroutineScope.launchUI(handler: CoroutineExceptionHandler, callback: suspend () -> Unit) =
    launch(Dispatchers.Main + handler) { callback() }

fun CoroutineScope.launchIO(callback: suspend () -> Unit) = launch(Dispatchers.IO) { callback() }

fun CoroutineScope.launchCalculation(callback: suspend () -> Unit) = launch(Dispatchers.Default) { callback() }

fun CoroutineScope.launchIO(handler: CoroutineExceptionHandler, callback: suspend () -> Unit) =
    launch(Dispatchers.IO + handler) { callback() }

suspend fun <T> withIO(callback: suspend () -> T) = withContext(Dispatchers.IO) { callback() }

suspend fun <T> withUI(callback: suspend () -> T) = withContext(Dispatchers.Main) { callback() }

fun <T> CoroutineScope.asyncIO(callback: suspend () -> T) = async(Dispatchers.IO) { callback() }

fun createHandler(block: (Throwable)->Unit) = CoroutineExceptionHandler { context, throwable ->
    Timber.e(throwable)
    block(throwable)
}
