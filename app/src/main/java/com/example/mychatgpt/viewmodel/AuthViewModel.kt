package com.example.mychatgpt.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mychatgpt.data.UserDataStore
import com.example.mychatgpt.data.database.MyChatGptFirebase
import com.example.mychatgpt.data.model.Account
import com.example.mychatgpt.util.FirebaseUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Authenticate user with Firebase.
 */
class AuthViewModel(private val dataStore: UserDataStore) : ViewModel() {
    fun createAccount(account: Account) {
        viewModelScope.launch {
            MyChatGptFirebase.addNewAccount(
                account.email,
                onSuccess = {
                    FirebaseUtil.createUserWithNameAndEmailAndPassword(
                        account.name, account.email, account.password, onSuccess = {
                            FirebaseUtil.verifyEmail({
                                Log.d("${TAG}2", "Email verification sent")
                            }, { Log.e("${TAG}2", it) })
                        }, onFailure = { errorMessage -> Log.e("${TAG}1", errorMessage) }
                    )
                },
                onFailure = { errorMessage -> Log.e(TAG, errorMessage) })
        }
    }

    fun login(account: Account) {
        viewModelScope.launch {
            FirebaseUtil.signIn(
                account.email,
                account.password,
                onSuccess = {
                    // store user email address and name
                    viewModelScope.launch(Dispatchers.IO) {
                        dataStore.saveEmail(account.email)
                        dataStore.saveName(account.name)
                    }
                },
                onFailure = { errorMessage -> Log.e(TAG, errorMessage) })
        }
    }

    companion object {
        private const val TAG = "AuthViewModel"
    }
}