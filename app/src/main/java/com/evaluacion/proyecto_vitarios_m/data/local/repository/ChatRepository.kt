package com.evaluacion.proyecto_vitarios_m.data.repository

import com.evaluacion.proyecto_vitarios_m.data.local.dao.ChatMessageDao
import com.evaluacion.proyecto_vitarios_m.data.local.entity.ChatMessageEntity
import kotlinx.coroutines.flow.Flow

class ChatRepository(private val chatMessageDao: ChatMessageDao) {

    val allMessages: Flow<List<ChatMessageEntity>> = chatMessageDao.getAllMessages()

    suspend fun insertMessage(message: ChatMessageEntity) {
        chatMessageDao.insertMessage(message)
    }

    suspend fun clearHistory() {
        chatMessageDao.deleteAllMessages()
    }
}