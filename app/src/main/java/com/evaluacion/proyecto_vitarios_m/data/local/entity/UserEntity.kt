package com.evaluacion.proyecto_vitarios_m.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val alias: String,
    val email: String,
    val emergencyContact: String = ""
)