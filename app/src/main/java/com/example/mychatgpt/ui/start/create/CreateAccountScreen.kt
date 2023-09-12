package com.example.mychatgpt.ui.start.create

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mychatgpt.Login
import com.example.mychatgpt.data.database.MyChatGptFirebase
import com.example.mychatgpt.data.model.Account
import com.example.mychatgpt.ui.theme.MyChatGPTTheme
import com.example.mychatgpt.util.FirebaseUtil
import com.example.mychatgpt.util.debug
import com.example.mychatgpt.util.isEmailValid
import com.example.mychatgpt.util.isPasswordValid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccountScreen(onClickNavigate: (String) -> Unit = {}) {
    var account by rememberSaveable(stateSaver = AccountSaver) { mutableStateOf(Account()) }
    var errorMessage by rememberSaveable { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .semantics { contentDescription = "Login Screen" }, contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Create New Account", fontSize = 24.sp)

            Spacer(modifier = Modifier.height(12.dp))

            // issue: text is not updated/user cannot type in TextField
            // solution: https://stackoverflow.com/a/74137042
            TextField(value = account.name, onValueChange = {
                account = account.copy(name = it)
            }, label = { Text(text = "Name") }, singleLine = true)

            Spacer(modifier = Modifier.height(12.dp))

            TextField(
                value = account.email, onValueChange = {
                    account = account.copy(email = it)
                }, label = { Text(text = "Email") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            TextField(value = account.password, onValueChange = {
                account = account.copy(password = it)
            }, label = { Text(text = "New Password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(onClick = {

                if (account.name.length !in 1..25) {
                    errorMessage = "name should be 1~25 characters"
                } else if (account.email.isBlank()) {
                    errorMessage = "email should not be blank"
                } else if (!isEmailValid(account.email)) {
                    errorMessage = "email is not valid"
                } else if (!isPasswordValid(account.password)) {
                    errorMessage = "password should be 6~16 characters"
                } else {


                    account = account.copy(name = account.name.trim(), email = account.email.trim())
                    MyChatGptFirebase.addNewAccount(account.email, onSuccess = {
                        FirebaseUtil.createUserWithNameAndEmailAndPassword(
                            account.name, account.email, account.password, onSuccess = {
                                // TODO: navigation is not immediate
                                onClickNavigate("${Login.route}/${account.email}")
                                FirebaseUtil.verifyEmail()
                                errorMessage = ""
                                account = Account() // reset account
                            }, onFailure = {
                                errorMessage = it
                            }
                        )
                    }, onFailure = {
                        errorMessage = it
                    })


                }
            }) {
                Text(text = "CREATE")
            }

            if (errorMessage.isNotBlank()) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = errorMessage, fontSize = 12.sp, color = Color.Red)
            }
        }
    }
}


/**
 * custom saver for Account
 */
val AccountSaver = run {
    mapSaver(save = {
        mapOf(
            "name" to it.name,
            "email" to it.email,
            "password" to it.password
        )
    },
        restore = {
            Account(it["name"] as String, it["email"] as String, it["password"] as String)
        })
}

@Preview(showBackground = true)
@Composable
private fun CreateAccountScreenPreview() {
    MyChatGPTTheme {
        CreateAccountScreen()
    }
}