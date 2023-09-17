package com.example.mychatgpt.ui.start.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mychatgpt.ChatList
import com.example.mychatgpt.Forget
import com.example.mychatgpt.data.UserDataStore
import com.example.mychatgpt.data.model.Account
import com.example.mychatgpt.ui.start.create.AccountSaver
import com.example.mychatgpt.ui.theme.MyChatGPTTheme
import com.example.mychatgpt.util.FirebaseUtil
import com.example.mychatgpt.util.debug
import com.example.mychatgpt.util.isEmailValid
import com.example.mychatgpt.util.isPasswordValid
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(email: String = "", onClickNavigate: (String) -> Unit = {}) {
    var account by rememberSaveable(stateSaver = AccountSaver) { mutableStateOf(Account(email = email)) }
    var errorMessage by rememberSaveable { mutableStateOf("") }
    val dataStore = UserDataStore(LocalContext.current)
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .semantics { contentDescription = "Login Screen" }, contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Login", fontSize = 24.sp)

            Spacer(modifier = Modifier.height(12.dp))

            TextField(value = account.email, onValueChange = {
                account = account.copy(email = it)
            }, label = { Text(text = "Email") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            TextField(value = account.password, onValueChange = {
                account = account.copy(password = it)
            }, label = { Text(text = "Password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            ClickableText(
                text = AnnotatedString("forget password?"),
                modifier = Modifier.align(Alignment.Start),
                style = TextStyle(textDecoration = TextDecoration.Underline), onClick = {
                    // TODO: navigate to forget screen
//                    onClickNavigate(Forget.route)
                })

            Spacer(modifier = Modifier.height(12.dp))

            Button(onClick = {
                if (account.email.isBlank()) {
                    errorMessage = "email should not be blank"
                } else if (!isEmailValid(account.email)) {
                    errorMessage = "email is not valid"
                } else if (!isPasswordValid(account.password)) {
                    errorMessage = "password should be 6~16 characters"
                } else {
                    FirebaseUtil.signIn(account.email, account.password, onSuccess = {
                        errorMessage = ""
                        // store user email address and name
                        coroutineScope.launch {
                            dataStore.saveEmail(account.email)
                            dataStore.saveName(account.name)
                        }
                        onClickNavigate(ChatList.route)
                    }, onFailure = { errorMessage = it })
                }

            }) {
                Text(text = "LOGIN")
            }

            if (errorMessage.isNotBlank()) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = errorMessage, color = Color.Red)
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