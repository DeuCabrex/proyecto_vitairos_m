package com.evaluacion.proyecto_vitarios_m.data.repository

import com.evaluacion.proyecto_vitarios_m.data.local.dao.UserDao
import com.evaluacion.proyecto_vitarios_m.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    val user: Flow<UserEntity?> = userDao.getUser()

    suspend fun saveUser(user: UserEntity) {
        userDao.insertUser(user)
    }
}