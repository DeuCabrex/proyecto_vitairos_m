package com.evaluacion.proyecto_vitarios_m.ui.viewmodel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.evaluacion.proyecto_vitarios_m.data.local.AppDatabase
import com.evaluacion.proyecto_vitarios_m.data.local.entity.CheckInEntity
import com.evaluacion.proyecto_vitarios_m.data.local.repository.CheckInRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CheckInViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CheckInRepository

    val allCheckIns: StateFlow<List<CheckInEntity>>

    init {
        val checkInDao = AppDatabase.getDatabase(application).checkInDao()
        repository = CheckInRepository(checkInDao)

        allCheckIns = repository.allCheckIns.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    }

    fun guardarCheckIn(moodId: Int, category: String, note: String, onFinished: () -> Unit) {
        viewModelScope.launch {
            val nuevoCheckIn = CheckInEntity(
                moodId = moodId,
                category = category,
                note = note
            )
            repository.insertCheckIn(nuevoCheckIn)
            onFinished()
        }
    }

    fun eliminarCheckIn(id: Long) {
        viewModelScope.launch {
            repository.deleteCheckIn(id)
        }
    }
}