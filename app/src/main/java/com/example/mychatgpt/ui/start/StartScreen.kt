package com.example.mychatgpt.ui.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mychatgpt.Create
import com.example.mychatgpt.Login
import com.example.mychatgpt.R
import com.example.mychatgpt.ui.theme.MyChatGPTTheme

@Composable
fun StartScreen(onClickNavigate: (String) -> Unit = {}) {
    Column(modifier = Modifier
        .fillMaxSize()
        .semantics { contentDescription = "Start Screen" }
        .padding(vertical = 100.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.mychatgpt),
            contentDescription = "MyChatGpt Logo"
        )
        Button(
            onClick = {
                onClickNavigate(Login.route)
            }, modifier = Modifier
                .fillMaxWidth(0.5f)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "LOGIN")
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {
                onClickNavigate(Create.route)
            }, modifier = Modifier
                .fillMaxWidth(0.5f)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "CREATE ACCOUNT")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun StartScreenPreview() {
    MyChatGPTTheme {
        StartScreen()
    }
}