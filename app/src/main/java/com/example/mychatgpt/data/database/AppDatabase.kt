package com.example.mychatgpt.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mychatgpt.data.dao.ChatroomDao
import com.example.mychatgpt.data.dao.MessageDao
import com.example.mychatgpt.data.model.ChatMessage
//import com.example.mychatgpt.data.model.ChatMessage
import com.example.mychatgpt.data.model.Chatroom

@Database(entities = [Chatroom::class, ChatMessage::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun chatroomDao(): ChatroomDao

    abstract fun messageDao(): MessageDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance

                return instance
            }
        }
    }

}