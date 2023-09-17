package com.example.mychatgpt.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "message",
    foreignKeys = [ForeignKey(
        entity = Chatroom::class,
        parentColumns = arrayOf("id"), childColumns = arrayOf("chatId"),
        onUpdate = ForeignKey.CASCADE, onDelete = ForeignKey.CASCADE
    )]
)
data class ChatMessage(
    val chatId: Int, @PrimaryKey(autoGenerate = true) val messageId: Int = 0,
    val role: String, val content: String, val timeStamp: String
)
