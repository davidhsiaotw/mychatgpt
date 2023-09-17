package com.example.mychatgpt.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mychatgpt.data.ChatRepository
import com.example.mychatgpt.data.model.Chatroom
import kotlinx.coroutines.flow.Flow

class ChatroomViewModel(private val chatRepository: ChatRepository) : ViewModel() {
    private val _chatrooms: Flow<List<Chatroom>> = chatRepository.getAllChatroom()
    val chatrooms: Flow<List<Chatroom>> = _chatrooms

    suspend fun createNewChatroom(chatroom: Chatroom = Chatroom(title = "New Chat", previewText = "")) {
        chatRepository.insertChatroom(chatroom)
    }
}