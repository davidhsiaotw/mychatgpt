package com.example.mychatgpt.ui.chat.chatlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.mychatgpt.ui.theme.MyChatGPTTheme

@Composable
fun ChatListScreen() {

    LazyColumn() {

    }
}

@Composable
private fun ChatItem(title: String = "") {
    Box() {
        Text(text = title)

    }
}

@Preview
@Composable
private fun ChatListScreenPreview() {
    MyChatGPTTheme {
        ChatListScreen()
    }
}

@Preview
@Composable
private fun ChatItemPreview() {
    MyChatGPTTheme {
        ChatItem()
    }
}