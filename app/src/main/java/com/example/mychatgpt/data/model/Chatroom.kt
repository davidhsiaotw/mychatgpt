package com.example.mychatgpt.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Chatroom(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, var title: String,
    @ColumnInfo(name = "preview") var previewText: String,
    @ColumnInfo(name = "last_update") var lastUpdateTime: String
)
