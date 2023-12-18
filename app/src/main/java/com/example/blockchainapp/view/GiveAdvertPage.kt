package com.example.blockchainapp.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.blockchainapp.model.UserData


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AdvertGivePage() {
    var userData by remember { mutableStateOf(UserData()) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        // Input fields with hints
        TextField(
            value = userData.name,
            onValueChange = { userData.name = it },
            label = { Text("Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        TextField(
            value = userData.surname,
            onValueChange = { userData.surname = it },
            label = { Text("Surname") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        TextField(
            value = userData.ssn,
            onValueChange = { userData.ssn = it },
            label = { Text("SSN") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        TextField(
            value = userData.phoneNumber,
            onValueChange = { userData.phoneNumber = it },
            label = { Text("Phone Number") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        TextField(
            value = userData.itemTitle,
            onValueChange = { userData.itemTitle = it },
            label = { Text("Item Title") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        TextField(
            value = userData.itemDescription,
            onValueChange = { userData.itemDescription = it },
            label = { Text("Item Description") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // Save button
        Button(
            onClick = {
                // Handle save button click
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {


        }
    }
}