package com.evaluacion.proyecto_vitarios_m.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.evaluacion.proyecto_vitarios_m.data.local.dao.ChatMessageDao
import com.evaluacion.proyecto_vitarios_m.data.local.dao.CheckInDao
import com.evaluacion.proyecto_vitarios_m.data.local.dao.UserDao
import com.evaluacion.proyecto_vitarios_m.data.local.entity.ChatMessageEntity
import com.evaluacion.proyecto_vitarios_m.data.local.entity.CheckInEntity
import com.evaluacion.proyecto_vitarios_m.data.local.entity.UserEntity

@Database(
    entities = [CheckInEntity::class, UserEntity::class, ChatMessageEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun checkInDao(): CheckInDao
    abstract fun userDao(): UserDao
    abstract fun chatMessageDao(): ChatMessageDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "vitarios_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}