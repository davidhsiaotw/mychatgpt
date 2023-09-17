package com.example.mychatgpt.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mychatgpt.data.model.ChatMessage
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {
    @Insert
    suspend fun insert(message: ChatMessage)

    @Query("SELECT * FROM message WHERE chatId = :chatroomId")
    fun getAllByChatroomId(chatroomId: Int): Flow<List<ChatMessage>>

    @Update
    suspend fun update(message: ChatMessage)

    @Delete
    suspend fun delete(message: ChatMessage)
}