package com.example.testboda.views

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import com.example.testboda.R
import com.example.testboda.entities.Constants.KEY_USER_NAME

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var textInput : AppCompatEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textInput = findViewById(R.id.main_view_card_view_text_input)
        button = findViewById(R.id.main_view_card_view_button)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        button.setOnClickListener {
            if (textInput.text.toString().isEmpty()) {
                Toast.makeText(this@MainActivity, getString(R.string.main_view_toast_text), Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this@MainActivity, QuizQuestionsActivity::class.java)
                intent.putExtra(KEY_USER_NAME, textInput.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}