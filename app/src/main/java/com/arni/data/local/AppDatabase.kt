package com.arni.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arni.data.local.db.entity.UserEntity


private const val DB_NAME = "todo_list_database"

@Database(
    entities = [
        UserEntity::class
    ], version = 1
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        fun create(context: Context): AppDatabase {

            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DB_NAME
            ).build()
        }
    }
}
