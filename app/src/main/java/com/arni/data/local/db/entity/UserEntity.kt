package com.arni.data.local.db.entity

import android.provider.BaseColumns
import androidx.room.ColumnInfo
import androidx.room.Entity
import com.arni.domain.model.UserDomain

@Entity(tableName = "user")
data class UserEntity(
    @ColumnInfo(name = BaseColumns._ID)
    val username: String? = null,
)

fun UserEntity.toDomain() = UserDomain(
   username = username
)
