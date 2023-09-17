package com.example.mychatgpt.ui.chat.chatlist

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mychatgpt.Chat
import com.example.mychatgpt.data.UserDataStore
import com.example.mychatgpt.util.FirebaseUtil
import com.example.mychatgpt.viewmodel.AppViewModelProvider
import com.example.mychatgpt.viewmodel.ChatViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatListTopBar(
    viewModel: ChatViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onClickNavigate: (String) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
//    val username = UserDataStore(LocalContext.current).getName.collectAsState(initial = "TITLE")
    val account = FirebaseUtil.getUserInfo()
    var showMenu by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    CenterAlignedTopAppBar(
        title = { Text(text = "Welcome, ${account.name}", fontSize = 18.sp) }, actions = {
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(imageVector = Icons.Outlined.MoreVert, contentDescription = null)
            }
            DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                DropdownMenuItem(text = { Text(text = "New Chat") }, onClick = {
                    onClickNavigate(Chat.route)
                    coroutineScope.launch { viewModel.createNewChatroom() }
                    viewModel.getLatestChatroom()
                }, leadingIcon = {
                    Icon(imageVector = Icons.Outlined.Add, contentDescription = null)
                })
            }
        },
        scrollBehavior = scrollBehavior
    )
}