package com.example.mychatgpt.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mychatgpt.data.model.Chatroom
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatroomDao {
    @Insert
    suspend fun insert(chatroom: Chatroom)

    @Query("SELECT * FROM chatroom")
    fun getAll(): Flow<List<Chatroom>>

    @Update
    suspend fun update(chatroom: Chatroom)

    @Delete
    suspend fun delete(chatroom: Chatroom)
}