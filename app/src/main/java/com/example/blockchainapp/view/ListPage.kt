package com.example.blockchainapp.view

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.blockchainapp.R
import com.example.blockchainapp.db.UserData
import com.example.blockchainapp.viewModel.ItemViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun ListAdvertisementsPage(viewModel: ItemViewModel, navController: NavController) {
    val itemList by viewModel.itemList.observeAsState(listOf())

    // head
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(2.dp, color = colorResource(id = R.color.BCYellow)),

    ) {
        Text(
            text = "Tüm İlanlar",
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
        )
    }
    Spacer(modifier = Modifier.width(10.dp))

    LazyColumn(
        modifier = Modifier
            .padding(top = 80.dp)
            .fillMaxSize(),
    ) {
        items(itemList) { item ->
            CardItem(
                item = item,
                onItemClick = {
                    // go to detail page
                    navController.navigate("DetailPage/${item.itemId}")
                    Log.d("TAGX", "ListPage: item: $item  and itemId:  ${item.itemId}")
                },
                viewModel = viewModel,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardItem(
    item: UserData?,
    onItemClick: () -> Unit,
    viewModel: ItemViewModel,
) {
    var isDialogVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Card(
        onClick = onItemClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp)
            .background(colorResource(id = R.color.transparent)),
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, colorResource(id = R.color.light_theme)),

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(colorResource(id = R.color.light_theme))
                .border(
                    1.dp,
                    colorResource(id = R.color.light_theme),
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.width(10.dp))

            Row(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Title: ",
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    color = colorResource(id = R.color.black),
                )

                item?.itemTitle?.let {
                    Text(
                        text = it,
                        modifier = Modifier
                            .align(Alignment.CenterVertically),
                        color = colorResource(id = R.color.black),
                    )
                }
            }

            Spacer(modifier = Modifier.width(10.dp))

            Row(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Price: ",
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    color = colorResource(id = R.color.black),
                )

                if (item != null) {
                    Text(
                        text = item.itemPrice.toString(),
                        modifier = Modifier
                            .align(Alignment.CenterVertically),
                        color = colorResource(id = R.color.black),
                    )
                }
            }
            Row(modifier = Modifier.weight(0.2f)) {
                IconButton(
                    onClick = { isDialogVisible = true },
                    modifier = Modifier.size(44.dp),
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        modifier = Modifier.size(34.dp),
                        tint = colorResource(R.color.red),
                    )
                }
//
            }
        }
    }
    if (isDialogVisible) {
        ShowDeleteConfirmationDialog(
            context = context,
            viewModel = viewModel,
            itemId = item!!.itemId,
            onDismiss = { isDialogVisible = false },
        )
    }
}

@Composable
fun ShowDeleteConfirmationDialog(
    context: android.content.Context,
    viewModel: ItemViewModel,
    itemId: Int,
    onDismiss: () -> Unit = {},
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Dikkat") },
        text = { Text(text = "İlanınız silinecektir") },
        confirmButton = {
            Button(
                onClick = {
                    viewModel.viewModelScope.launch {
                        try {
                            // Perform the deletion
                            withContext(Dispatchers.IO) {
                                // Perform the deletion on a background thread
                                viewModel.deleteButton(itemId)
                            }

                            // Show a success message
                            Toast.makeText(
                                context,
                                "İlan silindi.",
                                Toast.LENGTH_SHORT,
                            ).show()

                            // Dismiss the dialog
                            onDismiss()
                        } catch (e: Exception) {
                            // Handle exceptions
                            Log.e("TAG", "An error occurred: ${e.message}")
                            Toast.makeText(
                                context,
                                "An error occurred: ${e.message}",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }
                },
            ) {
                Text("Evet")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    // close the dialog without doing anything
                    onDismiss()
                },
            ) {
                Text(text = "Hayır")
            }
        },
    )
}
