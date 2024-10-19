package com.example.bcs371_homework_05

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

/**
 * Composable function that sets up the navigation graph for the app.
 *
 * This function initializes a navigation controller and defines the
 * different screens composables that can be navigated to within
 * the application, starting from the splash screen. The navigation
 * graph includes:
 *
 * SplashScreen: Displayed when the app is launched.
 * QuizActivity: Allows the user to participate in a quiz.
 * StatsScreen: Displays the results of the quiz, including the
 *   money earned, and provides an option to play again. It takes
 *   an argument moneyEarned passed through the navigation.
 */
@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash_screen"  ) {
        composable("splash_screen")
        {
            SplashScreen(navController)
        }

        composable("quizActivity_screen") {
            QuizActivity(navController)
        }

        composable("statsActivity_screen/{moneyEarned}") { backStackEntry ->
            val moneyEarned = backStackEntry.arguments?.getString("moneyEarned")?.toInt() ?: 0
            StatsScreen(
                moneyEarned = moneyEarned,
                onPlayAgain = {
                    navController.navigate("quizActivity_screen") {
                        popUpTo("quizActivity_screen") { inclusive = true }
                    }
                }
            )
        }
    }
}