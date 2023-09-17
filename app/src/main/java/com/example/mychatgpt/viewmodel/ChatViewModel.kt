package com.example.mychatgpt.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mychatgpt.data.ChatRepository
import com.example.mychatgpt.data.model.ChatMessage
import com.example.mychatgpt.data.model.Chatroom
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.time.ZonedDateTime

class ChatViewModel(private val chatRepository: ChatRepository) : ViewModel() {

    val chatrooms: Flow<List<Chatroom>> = chatRepository.getAllChatroom()

    var selectedChatroom: Flow<Chatroom> = flowOf()
        private set

    var messages: Flow<List<ChatMessage>> = flowOf()
        private set

    fun getLatestChatroom() {
        selectedChatroom = chatRepository.getLatestChatroom()
    }

    suspend fun createNewChatroom(
        chatroom: Chatroom = Chatroom(
            title = "New Chat", previewText = "", lastUpdateTime = ZonedDateTime.now().toString()
        )
    ) {
        chatRepository.insertChatroom(chatroom)
    }

    suspend fun deleteNewChatroom(chatroom: Chatroom) {
        chatRepository.deleteChatroom(chatroom)
    }

    fun getAllMessages(id: Int) {
        messages = chatRepository.getAllMessagesOfChatroom(id)
    }
}