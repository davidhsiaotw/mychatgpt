package com.example.mychatgpt.data

import com.example.mychatgpt.data.dao.ChatroomDao
import com.example.mychatgpt.data.dao.MessageDao
import com.example.mychatgpt.data.model.ChatMessage
import com.example.mychatgpt.data.model.Chatroom
import kotlinx.coroutines.flow.Flow

class OfflineChatRepository(
    private val chatroomDao: ChatroomDao, private val messageDao: MessageDao
) : ChatRepository {
    override suspend fun insertChatroom(chatroom: Chatroom) = chatroomDao.insert(chatroom)

    override fun getAllChatroom(): Flow<List<Chatroom>> = chatroomDao.getAll()

    override fun getLatestChatroom(): Flow<Chatroom> = chatroomDao.getLatest()

    override suspend fun updateChatroom(chatroom: Chatroom) = chatroomDao.update(chatroom)

    override suspend fun deleteChatroom(chatroom: Chatroom) = chatroomDao.delete(chatroom)

    override suspend fun insertMessage(chatMessage: ChatMessage) = messageDao.insert(chatMessage)

    override fun getAllMessagesOfChatroom(id: Int): Flow<List<ChatMessage>> =
        messageDao.getAllByChatroomId(id)

    override suspend fun updateMessage(chatMessage: ChatMessage) = messageDao.update(chatMessage)

    override suspend fun deleteMessage(chatMessage: ChatMessage) = messageDao.delete(chatMessage)
}