package com.example.blockchainapp.view

import android.os.Bundle
import android.util.Log
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.blockchainapp.model.UserData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavHost(
                navController = rememberNavController(),
                startDestination = "SplashScreen",
            )
        }
    }
}

@Composable
fun MainPage(navController: NavController) {
    // Compose function for the homepage
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
                text = "Otomotiv, Emlak, Kayıp, Çalıntı ve daha fazlası için ilan oluşturabilirsiniz.",
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
                        text = "Give Advert",
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
                    text = "List All",
                    modifier = Modifier,

                )
            }
        }
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String,
) {
    NavHost(navController = navController, startDestination = "SplashScreen") {
        composable("SplashScreen") {
            SplashScreen(navController)
        }
        composable("MainPage") {
            MainPage(navController)
        }
        composable("GiveAdvertPage") {
            AdvertGivePage()
        }
        composable("ListAdvertisementsPage") {
            ListAdvertisementsPage(
                itemList = listOf(
                    UserData("name1", "surname1", "ssn1"),
                    UserData("name2", "surname2", "ssn2"),
                ),
            )
        }
    }
}
