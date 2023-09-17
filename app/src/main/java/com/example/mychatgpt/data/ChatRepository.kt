package com.example.mychatgpt.data

import com.example.mychatgpt.data.model.Chatroom
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    suspend fun insertChatroom(chatroom: Chatroom)

    fun getAllChatroom(): Flow<List<Chatroom>>

    suspend fun updateChatroom(chatroom: Chatroom)

    suspend fun deleteChatroom(chatroom: Chatroom)
}