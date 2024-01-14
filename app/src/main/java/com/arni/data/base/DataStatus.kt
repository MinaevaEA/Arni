package com.arni.data.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

sealed interface DataStatus<in T : Any> {

    data class Success<T : Any>(val data: T) : DataStatus<T>

    data class Error<T : Any>(val ex: Exception, var data: T? = null) : DataStatus<T>

    object Loading : DataStatus<Any>

}

sealed interface CompletableStatus {

    object Success : CompletableStatus

    data class Error(val ex: Exception) : CompletableStatus

    object Loading : CompletableStatus

}

inline fun <reified T : Any, K : Any> DataStatus<K>.mapTo(mapper: (obj: K) -> T): DataStatus<T> {
    return when (this) {
        is DataStatus.Success -> DataStatus.Success(mapper(this.data))
        is DataStatus.Error -> DataStatus.Error(this.ex, this.data?.let(mapper))
        else -> DataStatus.Loading
    }
}

fun <T : Any> DataStatus<T>.toCompletable() = when(this) {
    is DataStatus.Success -> CompletableStatus.Success
    is DataStatus.Error -> CompletableStatus.Error(this.ex)
    is DataStatus.Loading -> CompletableStatus.Loading
}
suspend fun <T: Any> Flow<DataStatus<T>>.awaitResult(): DataStatus<T> = this.first {
    it is DataStatus.Success || it is DataStatus.Error
}

suspend fun <T: Any> Flow<DataStatus<T>>.awaitResultOrNull(): T? = this.first {
    it is DataStatus.Success || it is DataStatus.Error
}
    .let {
        (it as? DataStatus.Success)?.data
    }
