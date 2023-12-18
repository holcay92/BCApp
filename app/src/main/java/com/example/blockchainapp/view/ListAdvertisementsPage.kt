package com.example.blockchainapp.view

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.blockchainapp.model.UserData

@Composable
fun ListAdvertisementsPage(itemList: List<UserData>) {

    LazyColumn(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()) {
        items(itemList) { item ->
            Text(text = item.name, modifier = Modifier.padding(8.dp))
            Text(text = item.surname, modifier = Modifier.padding(8.dp))
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}