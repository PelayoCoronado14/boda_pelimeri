package com.example.testboda

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import com.example.testboda.Constants.USER_NAME
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var textInput : AppCompatEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor
        super.onCreate(savedInstanceState)
        // This is used to align the xml view to this class
        setContentView(R.layout.activity_main)
        textInput = findViewById(R.id.home_view_card_view_text_input)
        button = findViewById(R.id.home_view_card_view_button)

        // To hide the status bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        button.setOnClickListener {

            if (textInput.text.toString().isEmpty()) {

                Toast.makeText(this@MainActivity, "Please enter your name", Toast.LENGTH_SHORT)
                        .show()
            } else {

                val intent = Intent(this@MainActivity, QuizQuestionsActivity::class.java)
                // TODO (STEP 2: Pass the name through intent using the constant variable which we have created.)
                // START
                intent.putExtra(USER_NAME, textInput.text.toString())
                // END
                startActivity(intent)
                finish()
            }
        }
    }
}
}