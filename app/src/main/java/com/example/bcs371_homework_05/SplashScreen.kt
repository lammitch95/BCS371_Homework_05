package com.example.bcs371_homework_05

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bcs371_homework_05.ui.theme.BCS371_Homework_05Theme
import kotlinx.coroutines.delay

/**
 * Composable function representing the splash screen of the application.
 *
 * The SplashScreen function displays the splash screen view, which includes
 * an image and a title. It also handles navigation to the quiz activity screen
 * after a delay of 3 seconds.
 *
 * The NavController used to navigate between screens in the app.
 */

@Composable
fun SplashScreen(navController: NavController){
    SplashScreenView()
    LaunchedEffect(key1 = true) {
        delay(3000)
        navController.navigate("quizActivity_screen")
    }

}

@Composable
fun SplashScreenView(){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_monetization_on_24),
                contentDescription = "SplashLogo",
                modifier = Modifier.size(200.dp),
                colorFilter = ColorFilter.tint(Color(23, 128, 0, 255))
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Who Wants to Be a Millionaire?",
                fontSize = 25.sp
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){
    BCS371_Homework_05Theme {
        SplashScreenView()
    }
}