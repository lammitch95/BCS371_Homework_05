package com.example.bcs371_homework_05

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Composable function that displays the statistics screen at the end of the game.
 *
 * The StatsScreen function presents the player's performance, including the number of correct answers,
 * total money earned, and provides a button to play again. It features vertical scrolling for
 * usability in case of a small screen size.
 *
 * The total money earned by the player, used to display statistics.
 *  function that is triggered when the player clicks the "Play Again" button.
 */

@Composable
fun StatsScreen(
    moneyEarned: Int,
    onPlayAgain: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Game over!",
            fontSize = 30.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        Text(
            text = "Here are your statistics",
            fontSize = 30.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        Text(
            text = "Amount Correct",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        Text(
            text = "${moneyEarned/100} / 7",
            fontSize = 24.sp,
            color = Color(11, 131, 1, 255),
            modifier = Modifier.padding(bottom = 20.dp)
        )

        Text(
            text = "Total Earnings",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        Text(
            text = "$$moneyEarned",
            fontSize = 24.sp,
            color = Color(11, 131, 1, 255),
            modifier = Modifier.padding(bottom = 30.dp)
        )

        Button(
            onClick = onPlayAgain,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0, 88, 0, 255)
            )
        ) {
            Text(
                text = "Play Again",
                modifier = Modifier
                    .padding(start = 5.dp, end = 5.dp),
                fontSize = 20.sp

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StatsScreenPreview() {
    StatsScreen(moneyEarned = 500) {
        println("Play Again clicked")
    }
}