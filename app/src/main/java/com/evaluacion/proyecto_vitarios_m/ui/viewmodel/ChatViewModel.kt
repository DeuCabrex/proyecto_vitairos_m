package com.evaluacion.proyecto_vitarios_m.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.evaluacion.proyecto_vitarios_m.data.local.AppDatabase
import com.evaluacion.proyecto_vitarios_m.data.local.entity.ChatMessageEntity
import com.evaluacion.proyecto_vitarios_m.data.repository.ChatRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ChatViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ChatRepository
    val messages: StateFlow<List<ChatMessageEntity>>

    init {
        val dao = AppDatabase.getDatabase(application).chatMessageDao()
        repository = ChatRepository(dao)

        messages = repository.allMessages.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    }

    fun sendMessage(userText: String) {
        if (userText.isBlank()) return

        viewModelScope.launch {
            // 1. Guardar mensaje del usuario
            val userMsg = ChatMessageEntity(
                sender = "USER",
                message = userText.trim()
            )
            repository.insertMessage(userMsg)

            // 2. Simular respuesta automática del asistente (offline)
            delay(1000)
            val botReply = generarRespuestaAutomatica(userText)
            val botMsg = ChatMessageEntity(
                sender = "BOT",
                message = botReply
            )
            repository.insertMessage(botMsg)
        }
    }

    private fun generarRespuestaAutomatica(input: String): String {
        val lower = input.lowercase()
        return when {
            lower.contains("triste") || lower.contains("mal") ->
                "Lamento que te sientas así. Recuerda tomarte una pausa y respirar profundo. ¿Quieres registrar este sentimiento en tu Check-in?"
            lower.contains("ansioso") || lower.contains("ansiedad") ->
                "La ansiedad puede ser abrumadora. Intenta inhalar en 4 segundos y exhalar en 6 segundos. Estoy aquí para escucharte."
            lower.contains("hola") || lower.contains("buenas") ->
                "¡Hola! ¿Cómo te encuentras el día de hoy? Estoy aquí para brindarte soporte emocional."
            else ->
                "Gracias por compartir esto conmigo. Registrar lo que sientes es un gran paso para cuidar tu salud mental."
        }
    }
}