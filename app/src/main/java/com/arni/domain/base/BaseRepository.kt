package com.arni.domain.base


import com.arni.remote.model.response.CompletableData
import com.arni.remote.model.response.DataWrapper
import com.arni.data.base.CompletableStatus
import com.arni.data.base.DataStatus
import com.arni.ext.withIO
import com.arni.remote.model.response.toCompletableStatus
import com.arni.remote.model.response.toDataStatus
import retrofit2.HttpException
import timber.log.Timber


interface BaseRepository {
    suspend fun <T : Any> handleRequest(requestFunc: suspend () -> DataWrapper<T>): DataStatus<T> {
        return try {
            requestFunc().toDataStatus()
        } catch (ex: Exception) {
            Timber.e(ex)
            DataStatus.Error(ex)
        }
    }

    suspend fun handleCompletableRequest(requestFunc: suspend () -> CompletableData): CompletableStatus {
        return try {
            withIO { requestFunc() }.toCompletableStatus()
        } catch (ex: HttpException) {
            Timber.e(ex)
            CompletableStatus.Error(ex)
        }
    }
}
