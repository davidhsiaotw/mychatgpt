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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mychatgpt.data.model.Account
import com.example.mychatgpt.ui.theme.MyChatGPTTheme
import com.example.mychatgpt.util.debug
import com.example.mychatgpt.util.isPasswordValid
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccountScreen(title: String = "Create New Account") {
    var account by rememberSaveable(stateSaver = AccountSaver) { mutableStateOf(Account()) }
    var errorMessage by rememberSaveable { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .semantics { contentDescription = "Login Screen" }, contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = title, fontSize = 24.sp)

            Spacer(modifier = Modifier.height(12.dp))

            // issue: text is not updated/user cannot type in TextField
            // solution: https://stackoverflow.com/a/74137042
            TextField(value = account.name, onValueChange = {
                account = account.copy(name = it)
            }, label = { Text(text = "Name") })

            Spacer(modifier = Modifier.height(12.dp))

            TextField(value = account.email, onValueChange = {
                account = account.copy(email = it)
            }, label = { Text(text = "Email") })

            Spacer(modifier = Modifier.height(12.dp))

            TextField(value = account.password, onValueChange = {
                account = account.copy(password = it)
            }, label = { Text(text = "New Password") })

            Spacer(modifier = Modifier.height(12.dp))

            Button(onClick = {
                if (!isPasswordValid(account.password)) {
                    // check if password's length is at least six characters
                    errorMessage = "password should be minimum 6 characters"
                } else {
                    val firebaseAuth = FirebaseAuth.getInstance()

                    firebaseAuth.fetchSignInMethodsForEmail(account.email).addOnCompleteListener {
                        if (it.isSuccessful) {
                            it.result.signInMethods?.size.let { methods ->
                                if (methods != null) {
                                    // check if email already exists
                                    if (methods == 0) {
                                        errorMessage = ""

                                        // create user in Firebase Authentication
                                        firebaseAuth.createUserWithEmailAndPassword(
                                            account.email, account.password
                                        ).addOnCompleteListener { authTask ->
                                            if (authTask.isSuccessful) {
                                                errorMessage = ""

                                                // set name for the user
                                                firebaseAuth.currentUser?.updateProfile(
                                                    userProfileChangeRequest {
                                                        displayName = account.name
                                                    })?.addOnCompleteListener { setName ->
                                                    if (setName.isSuccessful) {
                                                        errorMessage = "complete"

                                                    } else {
                                                        errorMessage = setName.exception?.message
                                                            ?: "something wrong when setting name"
                                                    }

                                                }
                                            } else {
                                                errorMessage = authTask.exception?.message
                                                    ?: "something wrong when creating account"
                                            }
                                        }
                                    } else {
                                        errorMessage = "The email already exists"
                                    }
                                } else {
                                    errorMessage = it.exception?.message
                                        ?: "something wrong when checking duplicate email"
                                }
                            }
                        } else {
                            errorMessage = it.exception?.message
                                ?: "something wrong when checking duplicate email"
                        }
                    }


                }


            }) {
                Text(text = "CREATE")
            }

            if (errorMessage == "complete")
                debug("CreateAccountScreen", "NAVIGATE TO LOGIN SCREEN!")
            else if (errorMessage.isNotBlank()) {
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