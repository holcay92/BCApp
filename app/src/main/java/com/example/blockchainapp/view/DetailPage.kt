package com.example.blockchainapp.view

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blockchainapp.R
import com.example.blockchainapp.viewModel.ItemViewModel

@Composable
fun DetailPage(viewModel: ItemViewModel, argItemId: Int) {
    val item by viewModel.item.observeAsState()
    viewModel.getItemById(argItemId)
    val ctx = LocalContext.current

    var showDetails by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(2.dp, color = colorResource(id = R.color.BCYellow)),
    ) {
        Text(
            text = "İlan Detayları",
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp)
            .background(colorResource(id = R.color.BCYellow)),
    ) {
        if (item != null) {
            ItemTitle(title = "İlan Başlığı", value = item!!.itemTitle)
            ItemTitle(title = "İlan Fiyatı", value = item!!.itemPrice.toString())
            item!!.itemDescription?.let { ItemTitle(title = "İlan Açıklaması", value = it) }

            // Check if details should be shown
            if (showDetails) {
                ItemTitle(title = "İlan Sahibi", value = "${item!!.name} ${item!!.surname}")
                ItemTitle(title = "Telefon Numarası", value = item!!.phoneNumber)

                // Show dial button
                Button(
                    onClick = {
                        // dial the number
                        val u = Uri.parse("tel:" + item!!.phoneNumber)

                        val i = Intent(Intent.ACTION_DIAL, u)
                        try {
                            ctx.startActivity(i)
                        } catch (s: SecurityException) {
                            Toast.makeText(ctx, "An error occurred", Toast.LENGTH_LONG)
                                .show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.light_theme)),
                    modifier = Modifier.padding(top = 8.dp).align(Alignment.CenterHorizontally),
                ) {
                    Text("Ara")
                }
            } else {
                // Show "Show" button
                Button(
                    onClick = { showDetails = true },
                    modifier = Modifier.padding(top = 8.dp).align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.light_theme)),

                ) {
                    Text("İletişim Bilgilerini Göster")
                }
            }
        }

        Spacer(modifier = Modifier.width(10.dp))
    }
}

@Composable
fun ItemTitle(title: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "$title:",
            fontSize = 20.sp,
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = value,
            color = Color.Black,
            fontSize = 20.sp,
            fontFamily = FontFamily.SansSerif,
            textAlign = TextAlign.Center,
        )
    }
}
