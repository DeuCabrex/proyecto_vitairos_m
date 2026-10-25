package com.evaluacion.proyecto_vitarios_m.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.evaluacion.proyecto_vitarios_m.data.local.AppDatabase
import com.evaluacion.proyecto_vitarios_m.data.local.entity.UserEntity
import com.evaluacion.proyecto_vitarios_m.data.repository.UserRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository
    val userState: StateFlow<UserEntity?>

    init {
        val userDao = AppDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)

        userState = repository.user.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )
    }

    fun guardarUsuario(alias: String) {
        viewModelScope.launch {
            val usuario = UserEntity(
                id = 1,
                alias = alias,
                email = ""
            )
            repository.saveUser(usuario)
        }
    }
}