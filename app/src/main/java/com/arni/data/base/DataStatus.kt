package com.arni.data.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

interface Fail
open class Error(private val message: String): Fail {
    override fun toString(): String = message
}

class ErrorConnection: Error("Error connection")

sealed class Result<out R> {

    class Failure constructor(val fail: Fail): Result<Nothing>() {
        override fun toString(): String = "Failure($fail)"
    }

    class Success<out S> constructor(val value: S): Result<S>() {
        override fun toString(): String = "Failure($value)"
    }

    inline fun <T> fold(onFailure: (Fail) -> T, onSuccess: (R) -> T): T =
        when (this) {
            is Failure -> onFailure(this.fail)
            is Success -> onSuccess(value)
        }

    inline fun successEffect(block: (R) -> Unit): Result<R> =
        fold({ Result.Failure(it) }, { block(it); Result.Success(it) })

    inline fun failureEffect(block: (Fail) -> Unit): Result<R> =
        fold({ block(it); Result.Failure(it) }, { Result.Success(it) })
}

fun someRequestFromNetwork(errorCode: Int): Result<String> {
    return when(errorCode) {
        1 -> Result.Failure(Error("Some message"))
        2 -> Result.Failure(ErrorConnection())
        else -> Result.Success("Success request")
    }
}

fun someEvent(): Result<Nothing> = TODO()

fun example() {
    val requestResult: Result<String> = someRequestFromNetwork(2)

    requestResult.fold(
        onFailure = { fail -> println("Error: $fail") },
        onSuccess = { value -> println("Success: $value") }
    )

    requestResult
        .successEffect { value -> println("Success: $value") }
        .failureEffect { fail -> println("Error: $fail") }

    requestResult
        .successEffect { value -> println("Success: $value") }

    requestResult
        .failureEffect { fail -> println("Error: $fail") }

    someEvent().successEffect { TODO() }
}

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
