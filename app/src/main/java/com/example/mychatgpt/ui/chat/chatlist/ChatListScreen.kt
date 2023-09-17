package com.example.mychatgpt.ui.chat.chatlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mychatgpt.data.model.Chatroom
import com.example.mychatgpt.ui.theme.MyChatGPTTheme
import com.example.mychatgpt.viewmodel.AppViewModelProvider
import com.example.mychatgpt.viewmodel.ChatroomViewModel

@Composable
fun ChatListScreen(viewModel: ChatroomViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    val chatrooms by viewModel.chatrooms.collectAsState(initial = mutableListOf())

    LazyColumn(modifier = Modifier) {
        items(chatrooms) {
            ChatItem(chatroom = it)
        }
    }
}

@Composable
private fun ChatItem(chatroom: Chatroom) {
    Column() {
        Text(text = chatroom.title, fontSize = 24.sp)
        Text(text = chatroom.previewText)
    }
}

@Preview(showBackground = true)
@Composable
private fun ChatListScreenPreview() {
    MyChatGPTTheme {
        ChatListScreen()
    }
}

@Preview(showBackground = true)
@Composable
private fun ChatItemPreview() {
    MyChatGPTTheme {
        ChatItem(Chatroom(title = "New Chat", previewText = "Part of latest message"))
    }
}