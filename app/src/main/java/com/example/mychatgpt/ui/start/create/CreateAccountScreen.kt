package com.example.mychatgpt.ui.start.create

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mychatgpt.ui.theme.MyChatGPTTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccountScreen(title: String = "Create New Account") {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .semantics { contentDescription = "Login Screen" }, contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = title, fontSize = 24.sp)

            Spacer(modifier = Modifier.height(12.dp))

            TextField(value = "", onValueChange = {
                // TODO: change text
            }, label = { Text(text = "Name") })

            Spacer(modifier = Modifier.height(12.dp))

            TextField(value = "", onValueChange = {
                // TODO: change text
            }, label = { Text(text = "Email") })

            Spacer(modifier = Modifier.height(12.dp))

            TextField(value = "", onValueChange = {
                // TODO: change text
            }, label = { Text(text = "New Password") })

            Spacer(modifier = Modifier.height(12.dp))

            TextField(value = "", onValueChange = {
                // TODO: change text
            }, label = { Text(text = "Confirm Password") })

            Spacer(modifier = Modifier.height(12.dp))

            Button(onClick = {
                // TODO: navigate to login screen
            }) {
                Text(text = "CREATE")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CreateAccountScreenPreview() {
    MyChatGPTTheme {
        CreateAccountScreen()
    }
}