package com.example.mychatgpt.ui.chat.chatlist

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mychatgpt.Chat
import com.example.mychatgpt.data.model.Chatroom
import com.example.mychatgpt.ui.theme.MyChatGPTTheme
import com.example.mychatgpt.viewmodel.AppViewModelProvider
import com.example.mychatgpt.viewmodel.ChatViewModel

@Composable
fun ChatListScreen(
    viewModel: ChatViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onClickNavigate: (String) -> Unit = {}
) {
    val chatrooms by viewModel.chatrooms.collectAsState(initial = mutableListOf())

    LazyColumn(modifier = Modifier) {
        items(chatrooms) {
            ChatItem(chatroom = it, onClickNavigate = onClickNavigate)
            Divider()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChatItem(chatroom: Chatroom, onClickNavigate: (String) -> Unit = {}) {
    OutlinedCard(onClick = {
        onClickNavigate(Chat.route)
    }, modifier = Modifier.fillMaxWidth(), shape = RectangleShape) {
        Text(text = chatroom.title)
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
        ChatItem(Chatroom(0, "New Chat", "Part of latest message", ""))
    }
}