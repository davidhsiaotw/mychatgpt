package com.example.mychatgpt.ui.chat

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mychatgpt.viewmodel.AppViewModelProvider
import com.example.mychatgpt.viewmodel.ChatViewModel

@Composable
fun ChatScreen(viewModel: ChatViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    val messages by viewModel.messages.collectAsState(initial = mutableListOf())


}

@Composable
private fun ChatBox() {

}

@Preview(showBackground = true)
@Composable
private fun ChatScreenPreview() {

}