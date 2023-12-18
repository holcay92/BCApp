package com.example.blockchainapp.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blockchainapp.R
import com.example.blockchainapp.db.UserData
import com.example.blockchainapp.viewModel.ItemViewModel

@Composable
fun ListAdvertisementsPage(viewModel: ItemViewModel) {
    val itemList by viewModel.itemList.observeAsState(listOf())

    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
    ) {
        items(itemList) { item ->
            Text(text = item.name, modifier = Modifier.padding(8.dp))
            Text(text = item.surname, modifier = Modifier.padding(8.dp))
            Text(text = item.ssn, modifier = Modifier.padding(8.dp))
            Text(text = item.phoneNumber, modifier = Modifier.padding(8.dp))
            Text(text = item.itemTitle, modifier = Modifier.padding(8.dp))
            item.itemDescription?.let { Text(text = it, modifier = Modifier.padding(8.dp)) }
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardItem(
    item: UserData?,
    onItemClick: () -> Unit,
    itemViewModel: ItemViewModel,
) {
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
                .background(colorResource(id = R.color.light_theme))
                .border(
                    1.dp,
                    colorResource(id = R.color.light_theme),
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.width(10.dp))
            item?.name?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .weight(2f)
                        .align(Alignment.CenterVertically),
                    color = colorResource(id = R.color.light_theme),
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            item?.surname?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .weight(2f)
                        .align(Alignment.CenterVertically),
                    color = colorResource(id = R.color.light_theme),
                )
            }
            item?.itemTitle?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .weight(2f)
                        .align(Alignment.CenterVertically),
                    color = colorResource(id = R.color.light_theme),
                )
            }
        }
    }
}
