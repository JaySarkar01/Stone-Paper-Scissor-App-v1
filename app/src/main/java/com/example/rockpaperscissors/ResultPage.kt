package com.example.rockpaperscissors

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultPage : AppCompatActivity() {

    private var myPoints: Int = 0
    private var comPoints: Int = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_page)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val selectedOption = intent.getStringExtra("selectedOption")
        val myResult: TextView = findViewById(R.id.myR)
        val myImg: ImageView = findViewById(R.id.imageView4)

        myResult.text = "You selected $selectedOption!"

        when (selectedOption) {
            "Rock" -> myImg.setImageResource(R.drawable.fist)
            "Paper" -> myImg.setImageResource(R.drawable.handpaper)
            "Scissor" -> myImg.setImageResource(R.drawable.scissors)
        }

        findViewById<Button>(R.id.button).setOnClickListener {
            playGame(selectedOption)
        }

        playGame(selectedOption)
    }

    private fun playGame(selectedOption: String?) {
        val coImg: ImageView = findViewById(R.id.imageView5)
        val options = listOf("Rock", "Paper", "Scissor")
        val cChoice: String = options.random()

        when (cChoice) {
            "Rock" -> coImg.setImageResource(R.drawable.fist)
            "Paper" -> coImg.setImageResource(R.drawable.handpaper)
            "Scissor" -> coImg.setImageResource(R.drawable.scissors)
        }

        selectedOption?.let {
            showResult(it, cChoice)
        }
    }

    private fun showResult(myChoice: String, computerChoice: String) {
        val resultText: TextView = findViewById(R.id.ResultText)
        val result: String

        if (myChoice == computerChoice) {
            result = "Draw !!! 😂"
        } else {
            result = when {
                myChoice == "Rock" && computerChoice == "Paper" -> {
                    comPoints++
                    "Computer Wins !! 😢"
                }
                myChoice == "Rock" && computerChoice == "Scissor" -> {
                    myPoints++
                    "You Win !! 🥳"
                }
                myChoice == "Paper" && computerChoice == "Rock" -> {
                    myPoints++
                    "You Win !! 🥳"
                }
                myChoice == "Paper" && computerChoice == "Scissor" -> {
                    comPoints++
                    "Computer Wins !! 😢"
                }
                myChoice == "Scissor" && computerChoice == "Rock" -> {
                    comPoints++
                    "Computer Wins !! 😢"
                }
                myChoice == "Scissor" && computerChoice == "Paper" -> {
                    myPoints++
                    "You Win !! 🥳"
                }
                else -> ""
            }
        }

        resultText.text = "$result\n\nYou: $myPoints - Computer: $comPoints"
    }
}
