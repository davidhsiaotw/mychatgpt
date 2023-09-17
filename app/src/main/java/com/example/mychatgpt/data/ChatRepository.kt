package com.example.mychatgpt.data

import com.example.mychatgpt.data.model.ChatMessage
import com.example.mychatgpt.data.model.Chatroom
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    suspend fun insertChatroom(chatroom: Chatroom)

    fun getAllChatroom(): Flow<List<Chatroom>>

    fun getLatestChatroom(): Flow<Chatroom>

    suspend fun updateChatroom(chatroom: Chatroom)

    suspend fun deleteChatroom(chatroom: Chatroom)

    suspend fun insertMessage(chatMessage: ChatMessage)

    fun getAllMessagesOfChatroom(id: Int): Flow<List<ChatMessage>>

    suspend fun updateMessage(chatMessage: ChatMessage)

    suspend fun deleteMessage(chatMessage: ChatMessage)
}