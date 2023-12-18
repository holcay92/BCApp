package com.example.blockchainapp.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blockchainapp.R
import com.example.blockchainapp.model.UserData

@Composable
fun AdvertGivePage() {
    val userData by remember { mutableStateOf(UserData()) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(2.dp, color = colorResource(id = R.color.BCYellow)),

        ) {
            Text(
                text = "New Advert",
                modifier = Modifier
                    .align(CenterHorizontally),
                fontSize = 40.sp,
                fontWeight = Bold,
                fontFamily = FontFamily.SansSerif,
            )
        }
        // Input fields with hints

        // First Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
        ) {
            TextField(
                value = userData.name,
                onValueChange = { userData.name = it },
                label = { Text("Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(end = 8.dp),
            )

            TextField(
                value = userData.surname,
                onValueChange = { userData.surname = it },
                label = { Text("Surname") },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 8.dp),
            )
        }

        // Second Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
        ) {
            TextField(
                value = userData.ssn,
                onValueChange = { userData.ssn = it },
                label = { Text("SSN") },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(end = 8.dp),
            )

            TextField(
                value = userData.phoneNumber,
                onValueChange = { userData.phoneNumber = it },
                label = { Text("Phone Number") },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 8.dp),
            )
        }

        TextField(
            value = userData.itemTitle,
            onValueChange = { userData.itemTitle = it },
            label = { Text("Item Title") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
        )

        TextField(
            value = userData.itemDescription,
            onValueChange = { userData.itemDescription = it },
            label = { Text("Item Description") },
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(vertical = 8.dp),
        )

        // Save button
        Button(
            onClick = {
                // Handle save button click
            },
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.BCYellow)),
            content = {
                Text("Save")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
        )
    }
}
