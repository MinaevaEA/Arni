package com.arni.remote.model.response

import com.arni.data.base.CompletableStatus
import com.arni.data.base.DataStatus
import com.arni.remote.extentions.ArniRemoteExtentions
import java.io.IOException

data class DataWrapper<T>(
    val success: Boolean?,
    val message: String?,
    val data: T?
)

fun <K : Any> DataWrapper<K>.toDataStatus(): DataStatus<K> {
    return if (this.success == true && this.data != null) {
        DataStatus.Success(this.data)
    } else {
        DataStatus.Error(
            when {
                this.success != true -> {
                    if (this.message.isNullOrEmpty())
                        IllegalStateException(this.message)
                    else
                        ArniRemoteExtentions(this.message)
                }
                this.data == null -> NullPointerException(this.message)
                else -> IOException(this.message)
            }
        )
    }
}

data class CompletableData(
    val success: Boolean?,
    val message: String?
)

fun CompletableData.toCompletableStatus(): CompletableStatus {
    return if (this.success == true) {
        CompletableStatus.Success
    } else {
        CompletableStatus.Error(
            when {
                this.success != true -> { IllegalStateException(this.message) }
                else -> IOException(this.message)
            }
        )
    }
}

