package com.example.mychatgpt.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mychatgpt.data.UserDataStore
import com.example.mychatgpt.data.database.MyChatGptFirebase
import com.example.mychatgpt.data.model.Account
import com.example.mychatgpt.ui.start.AuthUiState
import com.example.mychatgpt.util.FirebaseUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Authenticate user with Firebase.
 */
class AuthViewModel(private val dataStore: UserDataStore) : ViewModel() {
    private val _uiState = MutableStateFlow(null as AuthUiState?)
    val uiState = _uiState.asStateFlow()

    fun createAccount(account: Account) {
        _uiState.update { AuthUiState.Loading }
        viewModelScope.launch {
            MyChatGptFirebase.addNewAccount(
                account.email,
                onSuccess = {
                    FirebaseUtil.createUserWithNameAndEmailAndPassword(
                        account.name, account.email, account.password, onSuccess = {
                            FirebaseUtil.verifyEmail({
                                Log.d("${TAG}2", "Email verification sent")
                                _uiState.update { AuthUiState.Success }
                                _uiState.update { null }
                            }, { Log.e("${TAG}2", it) })
                        }, onFailure = { errorMessage ->
                            Log.e("${TAG}1", errorMessage)
                            _uiState.update { AuthUiState.Failure(errorMessage) }
                        }
                    )
                },
                onFailure = { errorMessage ->
                    Log.e(TAG, errorMessage)
                    _uiState.update { AuthUiState.Failure(errorMessage) }
                })
        }
    }

    fun login(account: Account) {
        _uiState.update { AuthUiState.Loading }
        viewModelScope.launch {
            FirebaseUtil.signIn(
                account.email,
                account.password,
                onSuccess = {
                    _uiState.update { AuthUiState.Success }
                    // store user email address and name
                    viewModelScope.launch(Dispatchers.IO) {
                        dataStore.saveEmail(account.email)
                        dataStore.saveName(account.name)
                    }
                },
                onFailure = { errorMessage ->
                    _uiState.update { AuthUiState.Failure(errorMessage) }
                })
        }
    }

    companion object {
        private const val TAG = "AuthViewModel"
    }
}