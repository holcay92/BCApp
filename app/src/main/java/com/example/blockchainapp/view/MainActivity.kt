package com.example.blockchainapp.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.blockchainapp.R
import com.example.blockchainapp.viewModel.ItemViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<ItemViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavHost(
                viewModel,
                navController = rememberNavController(),
            )
        }
    }
}

@Composable
fun MainPage(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        Column {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, color = colorResource(id = R.color.BCYellow)),
            ) {
                // ILANOLUSTUR.COM headline
                Text(
                    text = "ILANOLUSTUR.COM",
                    fontFamily = FontFamily.Serif,
                    fontSize = 30.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(CenterHorizontally),
                )
            }
            Spacer(modifier = Modifier.padding(28.dp))

            Text(
                text = "Otomotiv, Emlak, Kayıp, Çalıntı.",
                fontFamily = FontFamily.Serif,
                fontSize = 20.sp,
                color = Color.Gray,
                modifier = Modifier
                    .padding(8.dp)
                    .align(CenterHorizontally),
            )
        }

        Row(
            modifier = Modifier
                .align(CenterHorizontally),
            verticalAlignment = Bottom,
        ) {
            // go to GiveAdvertPage
            Button(
                onClick = {
                    navController.navigate("GiveAdvertPage"); Log.i(
                        "Tag",
                        "MainPage: ",
                    )
                },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.BCYellow)),
                modifier = Modifier.weight(1f),
                content = {
                    Text(
                        text = "İlan Ver",
                        modifier = Modifier,
                    )
                },
            )
            Spacer(modifier = Modifier.weight(0.1f))

            // go to ListAdvertisementsPage
            Button(
                onClick = {
                    navController.navigate("ListAdvertisementsPage"); Log.i(
                        "Tag",
                        "MainPage: 2",
                    )
                },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.BCYellow)),
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = "İlanlara Bak",
                    modifier = Modifier,

                )
            }
        }
    }
}

@Composable
fun AppNavHost(
    viewModel: ItemViewModel,
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = "SplashScreen") {
        composable("SplashScreen") {
            SplashScreen(navController)
        }
        composable("MainPage") {
            MainPage(navController)
        }
        composable("GiveAdvertPage") {
            AdvertGivePage(viewModel)
        }
        composable("ListAdvertisementsPage") {
            ListAdvertisementsPage(viewModel)
        }
    }
}
