package com.example.mychatgpt.ui.start.forget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mychatgpt.R
import com.example.mychatgpt.ui.theme.MyChatGPTTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgetScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .semantics { contentDescription = "Login Screen" }, contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Forgot Your Password?", fontSize = 24.sp)

            Spacer(modifier = Modifier.height(12.dp))

            Image(
                painter = painterResource(id = R.drawable.forgotpassword),
                contentDescription = "Forgot Password",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            TextField(value = "", onValueChange = {
                // TODO: change text
            }, label = { Text(text = "Email") })

            Spacer(modifier = Modifier.height(12.dp))

            TextField(value = "", onValueChange = {
                // TODO: change text
            }, label = { Text(text = "Verification Code") }, trailingIcon = {
                ClickableText(text = AnnotatedString("SEND"), onClick = {
                    // TODO: send verification email
                })
                // TODO: after sending email, countdown 30 seconds
            })

            Spacer(modifier = Modifier.height(12.dp))

            Button(onClick = {
                // TODO: navigate to create account screen with title "Reset Account"
            }) {
                Text(text = "RESET ACCOUNT")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ForgetScreenPreview() {
    MyChatGPTTheme {
        ForgetScreen()
    }
}