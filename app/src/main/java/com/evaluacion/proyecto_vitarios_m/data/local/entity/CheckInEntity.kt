package com.evaluacion.proyecto_vitarios_m.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "checkins")
data class CheckInEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val moodId: Int,
    val category: String,
    val note: String,
    val timestamp: Long = System.currentTimeMillis()
)