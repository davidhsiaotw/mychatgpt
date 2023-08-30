package com.example.mychatgpt.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mychatgpt.ui.theme.MyChatGPTTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyChatGPTTheme {
                MyChatGPTApp()
            }
        }
    }
}

@Composable
fun MyChatGPTApp() {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyChatGPTTheme {
        MyChatGPTApp()
    }
}