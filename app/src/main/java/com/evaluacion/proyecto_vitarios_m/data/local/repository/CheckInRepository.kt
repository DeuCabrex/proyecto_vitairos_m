package com.evaluacion.proyecto_vitarios_m.data.local.repository

import com.evaluacion.proyecto_vitarios_m.data.local.dao.CheckInDao
import com.evaluacion.proyecto_vitarios_m.data.local.entity.CheckInEntity
import kotlinx.coroutines.flow.Flow

class CheckInRepository(private val checkInDao: CheckInDao) {

    val allCheckIns: Flow<List<CheckInEntity>> = checkInDao.getAllCheckIns()

    suspend fun insertCheckIn(checkIn: CheckInEntity) {
        checkInDao.insertCheckIn(checkIn)
    }

    suspend fun deleteCheckIn(id: Long) {
        checkInDao.deleteCheckIn(id)
    }
}