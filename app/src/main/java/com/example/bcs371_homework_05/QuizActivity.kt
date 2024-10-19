package com.example.bcs371_homework_05

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bcs371_homework_05.ui.theme.BCS371_Homework_05Theme



/*
 * Key Components:
 * Data Class:
 *   quizQuestion`: Represents a quiz question with text, answer options, and the index of the correct answer.
 *
 * Sample Questions: A list of predefined quiz questions covering various topics.
 *
 *  Composable Functions:
 *   `QuizActivity`: Displays the current quiz question, tracks the player's score, and manages navigation to the stats screen.
 *   `DisplayQuestions`: Renders the question and answer options, highlighting the selected answer.
 */


data class quizQuestion(
    val question: String,
    val answerList: List<String>,
    val correctAnswerIndex: Int
)

val questionsList= listOf(
    quizQuestion(
        "What is the capital of France?",
        listOf("Berlin", "Rome", "Paris", "Madrid"),
        2
    ),
    quizQuestion(
        "Which planet is known as the Red Planet?",
        listOf("Mars", "Venus", "Earth", "Jupiter"),
        0
    ),
    quizQuestion(
        "Which animal is known as the king of the jungle?",
        listOf("Elephant", "Gorilla", "Lion", "Tiger"),
        2
    ),
    quizQuestion(
        "What is the boiling point of water at sea level in Celsius?",
        listOf("150°C", "100°C", "50°C", "200°C"),
        1
    ),
    quizQuestion(
        "Which country is home to the Great Wall?",
        listOf("India", "Egypt", "Japan", "China"),
        3
    ),
    quizQuestion(
        "Who wrote the play 'Romeo and Juliet'?",
        listOf("Charles Dickens", "J.K. Rowling", "Mark Twain", "William Shakespeare"),
        3
    ),
    quizQuestion(
        "Which fruit is known for keeping the doctor away if eaten daily?",
        listOf("Banana", "Apple", "Mango", "Orange"),
        1
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizActivity(navController: NavController){

    var currQuestionIndex by remember { mutableIntStateOf(0) }
    var chosenAnswerIndex by remember { mutableStateOf<Int?>(null) }
    var trackMoneyScore by remember { mutableIntStateOf(0) }
    val currQuestion = questionsList[currQuestionIndex]

    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){

        Scaffold(
            bottomBar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                    ,
                    contentAlignment = Alignment.Center
                ){
                    Button(
                        onClick = {
                            if(chosenAnswerIndex == null){
                                Toast.makeText(context, "Please select an answer!", Toast.LENGTH_SHORT).show()
                            }else{
                                if (chosenAnswerIndex == currQuestion.correctAnswerIndex) {
                                    Toast.makeText(context, "Correct! +100", Toast.LENGTH_SHORT).show()
                                    trackMoneyScore += 100
                                } else {
                                    Toast.makeText(context, "Wrong! No Points Rewarded", Toast.LENGTH_SHORT).show()
                                }

                                if (currQuestionIndex < questionsList.size - 1) {
                                    currQuestionIndex++
                                    chosenAnswerIndex = null
                                } else {
                                    navController.navigate("statsActivity_screen/$trackMoneyScore")
                                }
                            }
                        },
                        shape = RoundedCornerShape(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(148, 147, 147, 255)
                        )

                    ) {
                        Text(
                            text = "CONFIRM",
                            color = Color(0, 0, 0, 255)
                        )
                    }
                }

            },
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(2, 114, 55, 255) // Change background color here
                    ),
                    title = {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ){
                            Text(
                                text = "Homework - Who Wants to Be A millionaire? ",
                                color = Color(255, 255, 255),
                                fontSize = 17.sp
                            )
                        }
                    },
                )

            }

        ){ innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "You Earned: $ $trackMoneyScore",
                    fontSize = 30.sp,
                    color = Color.Blue
                )
                Spacer(modifier = Modifier.height(40.dp))
                DisplayQuestions(
                    currQuestion,
                    chosenAnswerIndex,
                    onAnswerSelected = {chosenAnswerIndex = it}
                )
            }
        }


    }
}

@Composable
fun DisplayQuestions(currQuestion: quizQuestion, chosenAnswerIndex: Int?, onAnswerSelected: (Int) -> Unit){

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = currQuestion.question,
            fontSize = 22.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(10.dp))
        currQuestion.answerList.forEachIndexed { index, choice ->

            val highlightChoice = if (chosenAnswerIndex == index) {
                Color.Green
            }else{
                Color.Transparent
            }
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .background(highlightChoice)
                    .selectable(
                        selected = (chosenAnswerIndex == index),
                        onClick = { onAnswerSelected(index) }
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                    RadioButton(
                        selected = (chosenAnswerIndex == index),
                        onClick = {onAnswerSelected(index)}
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = choice,
                        fontSize = 20.sp
                    )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun QuizPreview(){
    BCS371_Homework_05Theme {
        QuizActivity(navController = rememberNavController())
    }
}