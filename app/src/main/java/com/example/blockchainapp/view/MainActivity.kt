package com.example.blockchainapp.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
          MainPage()
        }
    }
}

@Composable
fun MainPage() {
    // State to hold the list of items
    var itemList by remember { mutableStateOf(listOf<String>()) }

    // State to hold the current entered item
    var newItem by remember { mutableStateOf("") }

    // Compose function for the homepage
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        // Text field for entering items
        OutlinedTextField(
            value = newItem,
            onValueChange = { newItem = it },
            label = { Text("Enter an item") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    // Add the new item to the list and clear the text field
                    itemList = itemList + newItem
                    newItem = ""
                },
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
        )

        // LazyColumn to display the list of items
        LazyColumn {
            items(itemList) { item ->
                Text(text = item, modifier = Modifier.padding(8.dp))
            }
        }
    }
}
