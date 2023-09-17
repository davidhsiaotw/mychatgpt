package com.example.mychatgpt.viewmodel

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.mychatgpt.MyChatGptApplication

/**
 * [reference](https://github.com/google-developer-training/basic-android-kotlin-compose-training-inventory-app/blob/starter/app/src/main/java/com/example/inventory/ui/AppViewModelProvider.kt)
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ChatViewModel(myChatGptApplication().container.chatRepository)
        }
    }
}

fun CreationExtras.myChatGptApplication(): MyChatGptApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as MyChatGptApplication)