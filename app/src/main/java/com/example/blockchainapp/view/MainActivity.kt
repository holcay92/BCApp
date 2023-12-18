package com.example.blockchainapp.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blockchainapp.ui.theme.PurpleGrey40
import dagger.hilt.android.AndroidEntryPoint
import com.example.blockchainapp.R

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

    // Compose function for the homepage
    Column(
        modifier = Modifier
            .fillMaxSize()

            .background(Color.LightGray)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Column {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, color = colorResource(id = R.color.BCYellow))
            ) {
                //ILANOLUSTUR.COM headline
                Text(
                    text = "ILANOLUSTUR.COM",
                    fontFamily = FontFamily.Serif,
                    fontSize = 30.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(CenterHorizontally)
                )

            }
            Spacer(modifier = Modifier.padding(28.dp))

            Text(
                text = "Otomotiv, Emlak, Kayıp, Çalıntı ve daha fazlası için ilan oluşturabilirsiniz.",
                fontFamily = FontFamily.Serif,
                fontSize = 20.sp,
                color = Color.Gray,
                modifier = Modifier
                    .padding(8.dp)
                    .align(CenterHorizontally)
            )
        }




        Row(
            modifier = Modifier
                .align(CenterHorizontally)
                .background(colorResource(id = R.color.BCGray)), verticalAlignment = Bottom
        ) {
            //go to GiveAdvertPage
            Button(onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.BCYellow)),
                modifier = Modifier.weight(1f),
                content = {
                    Text(
                        text = "Give Advert", modifier = Modifier
                    )
                })
            Spacer(modifier = Modifier.weight(0.1f))

            //go to ListAdvertisementsPage
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.BCYellow)),
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = "List All", modifier = Modifier

                )
            }
        }

    }

}
