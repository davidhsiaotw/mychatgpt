package com.example.mychatgpt.ui.start

sealed class AuthUiState {
    object Success : AuthUiState()
    class Failure(val message: String) : AuthUiState()
    object Loading : AuthUiState()
}