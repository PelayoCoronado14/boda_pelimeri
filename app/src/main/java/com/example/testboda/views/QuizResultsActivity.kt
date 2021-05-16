package com.example.testboda.views

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.testboda.R
import com.example.testboda.entities.Constants.KEY_QUESTION_LIST
import com.example.testboda.entities.Constants.KEY_TIMESTAMP
import com.example.testboda.entities.Constants.KEY_USER_NAME
import com.example.testboda.entities.Question
import com.example.testboda.mail.GMailSender

class QuizResultsActivity :AppCompatActivity(){
    private var userName : String? = null
    private var userResult : Int = 0
    private var userTime : Long? = 0L
    private lateinit var questionList : ArrayList<Question>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_results)
        getExtras()
        getResults()
        setText()
        setListeners()
    }

    override fun onBackPressed() {
        return
    }

    private fun getExtras(){
        intent.extras.let {
            userName = it?.getString(KEY_USER_NAME)
            userTime = it?.getLong(KEY_TIMESTAMP)
            questionList = it?.getParcelableArrayList<Question>(KEY_QUESTION_LIST) as ArrayList<Question>
        }
    }

    private fun getResults(){
        for(question in questionList){

            if(question.answerSelected == question.correctAnswer){
                userResult++
            }
        }
    }

    private fun setText(){
        when(userResult){
            0,1,2,3,4 -> {
                findViewById<TextView>(R.id.questionary_results_title).text = getString(R.string.results_view_text_title_fail)
            }
            5,6,7 -> {
                findViewById<TextView>(R.id.questionary_results_title).text = getString(R.string.results_view_text_title_good)
            }
            8,9,10 -> {
                findViewById<TextView>(R.id.questionary_results_title).text = getString(R.string.results_view_text_title_excelent)
            }
        }
    }

    private fun setListeners(){
        findViewById<Button>(R.id.questionary_results_button).setOnClickListener{
            sendResultsByEmail()
        }
    }

    private fun sendResultsByEmail(){
        val dialog = ProgressDialog(this)
        dialog.setTitle("Sending Email")
        dialog.setMessage("Please wait")
        dialog.show()
        val sender = Thread(Runnable {
            try {
                val sender = GMailSender("bodapelimeri@gmail.com", "Bodapelimeri1007!")
                sender.sendMail(getString(R.string.results_view_text_result, userName, userResult.toString(), getUserTime()),
                        "This is the message body",
                        "bodapelimeri@gmail.com",
                        "bodapelimeri@gmail.com")
                dialog.dismiss()
            } catch (e: Exception) {
                Log.e("mylog", "Error: " + e.message)
            }
        })
        sender.start()
    }

    private fun getUserTime() : String{
        val tsLong = System.currentTimeMillis() / 1000
        val ts = tsLong.toString()
        var spendTime = 0L
        userTime?.let {
         spendTime = tsLong-it
        }



        return spendTime.toString()
    }
}
