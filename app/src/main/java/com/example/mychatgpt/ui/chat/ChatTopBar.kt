package com.example.mychatgpt.ui.chat

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mychatgpt.viewmodel.AppViewModelProvider
import com.example.mychatgpt.viewmodel.ChatViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTopBar(viewModel: ChatViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    val currentChatroom by viewModel.selectedChatroom.collectAsState(null)
    TopAppBar(
        title = { Text(text = currentChatroom?.title ?: "") }
    )
}