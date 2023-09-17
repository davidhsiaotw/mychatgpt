package com.example.mychatgpt.data

import android.content.Context
import com.example.mychatgpt.data.database.AppDatabase

/**
 * [reference](https://github.com/google-developer-training/basic-android-kotlin-compose-training-inventory-app/blob/starter/app/src/main/java/com/example/inventory/data/AppContainer.kt)
 */
interface AppContainer {
    val chatRepository: ChatRepository
}

/**
 * @see AppContainer
 */
class AppDataContainer(private val context: Context) : AppContainer {
    override val chatRepository: ChatRepository by lazy {
        OfflineChatRepository(AppDatabase.getDatabase(context).chatroomDao())
    }
}