package com.example.mychatgpt.data

import com.example.mychatgpt.data.dao.ChatroomDao
import com.example.mychatgpt.data.model.Chatroom
import kotlinx.coroutines.flow.Flow

class OfflineChatRepository(private val chatroomDao: ChatroomDao) : ChatRepository {
    override suspend fun insertChatroom(chatroom: Chatroom) = chatroomDao.insert(chatroom)

    override fun getAllChatroom(): Flow<List<Chatroom>> = chatroomDao.getAll()

    override suspend fun updateChatroom(chatroom: Chatroom) = chatroomDao.update(chatroom)

    override suspend fun deleteChatroom(chatroom: Chatroom) = chatroomDao.delete(chatroom)
}