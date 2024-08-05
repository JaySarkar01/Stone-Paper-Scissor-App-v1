package com.example.rockpaperscissors

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.findViewTreeViewModelStoreOwner

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val startButton: Button = findViewById(R.id.StartButton)
        val radioGroup: RadioGroup = findViewById(R.id.Rgroup)

        startButton.setOnClickListener{
            val selectedOption = when (radioGroup.checkedRadioButtonId){
                R.id.radioRock -> "Rock"
                R.id.radioPaper -> "Paper"
                R.id.radioScissor -> "Scissor"
                else -> null
            }
            if (selectedOption == null)
            {
                Toast.makeText(getApplicationContext(), "Please Select Any One Option!", Toast.LENGTH_LONG).show();
            }
            else{
                val intent = Intent(this,ResultPage::class.java)
                intent.putExtra("selectedOption",selectedOption)
                startActivity(intent)
            }


        }
    }
}
