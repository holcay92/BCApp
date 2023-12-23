package com.example.blockchainapp.view

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.blockchainapp.R
import com.example.blockchainapp.db.UserData
import com.example.blockchainapp.viewModel.ItemViewModel

@Composable
fun AdvertGivePage(viewModel: ItemViewModel, navController: NavController) {
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var ssn by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var itemTitle by remember { mutableStateOf("") }
    var itemDescription by remember { mutableStateOf("") }
    var itemPrice by remember { mutableStateOf("") }
    val context = LocalContext.current
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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(end = 8.dp),
            )

            TextField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                value = surname,
                onValueChange = { surname = it },
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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = ssn,
                onValueChange = { ssn = it },
                label = { Text("SSN") },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(end = 8.dp),
            )

            TextField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("Phone Number") },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 8.dp),
            )
        }

        TextField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            value = itemTitle,
            onValueChange = { itemTitle = it },
            label = { Text("Item Title") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
        )

        itemDescription.let {
            TextField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                value = it,
                onValueChange = { itemDescription = it },
                label = { Text("Item Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(vertical = 8.dp),
            )
        }
        TextField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            value = itemPrice,
            onValueChange = { itemPrice = it },
            label = { Text("Item Price") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
        )

        // Save button
        Button(
            onClick = {
                val userData = UserData(
                    itemId = 0,
                    name = name,
                    surname = surname,
                    ssn = ssn,
                    phoneNumber = phoneNumber,
                    itemTitle = itemTitle,
                    itemDescription = itemDescription.ifEmpty { null },
                    itemPrice = itemPrice.toIntOrNull(),
                )
                if (checkInputFields(userData)) {
                    viewModel.saveButton(userData)
                    Toast.makeText(
                        context,
                        "İlan eklendi.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    // clear input fields
                    clearInputFields(userData)
                    // go to main page
                    navController.navigate("MainPage")
                } else {
                    Toast.makeText(
                        context,
                        "Gerekli alanları doldurunuz.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.BCYellow)),
            content = {
                Text("Kaydet")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
        )
    }
}

fun checkInputFields(
    userData: UserData,
): Boolean {
    Log.i("Tagx", "checkInputFields: $userData")
    return userData.name.isNotEmpty() &&
        userData.surname.isNotEmpty() &&
        userData.ssn.isNotEmpty() &&
        userData.phoneNumber.isNotEmpty() &&
        userData.itemTitle.isNotEmpty() &&
        userData.itemPrice != null
}
fun clearInputFields(
    userData: UserData,
): Boolean {
    userData.name = ""
    userData.surname = ""
    userData.ssn = ""
    userData.phoneNumber = ""
    userData.itemTitle = ""
    userData.itemDescription = ""
    userData.itemPrice = null
    return true
}
