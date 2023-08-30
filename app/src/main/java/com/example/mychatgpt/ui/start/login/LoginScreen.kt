package com.example.mychatgpt.ui.start.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mychatgpt.ui.theme.MyChatGPTTheme
import com.example.mychatgpt.util.debug

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .semantics { contentDescription = "Login Screen" }, contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Login", fontSize = 24.sp)

            Spacer(modifier = Modifier.height(12.dp))

            TextField(value = "", onValueChange = {
                // TODO: change text
            }, label = { Text(text = "Email") })

            Spacer(modifier = Modifier.height(12.dp))

            TextField(value = "", onValueChange = {
                // TODO: change text
            }, label = { Text(text = "Password") })

            Spacer(modifier = Modifier.height(12.dp))

            ClickableText(
                text = AnnotatedString("forget password?"),
                modifier = Modifier.align(Alignment.Start), onClick = {
                    // TODO: navigate to forget screen
                })

            Spacer(modifier = Modifier.height(12.dp))

            Button(onClick = {
                // TODO: if first-time login, navigate to verification screen, otherwise chat list screen
            }) {
                Text(text = "LOGIN")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    MyChatGPTTheme {
        LoginScreen()
    }
}