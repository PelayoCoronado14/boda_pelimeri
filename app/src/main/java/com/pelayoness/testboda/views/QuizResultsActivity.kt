package com.pelayoness.testboda.views

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.pelayoness.testboda.R
import com.pelayoness.testboda.entities.Constants.KEY_QUESTION_LIST
import com.pelayoness.testboda.entities.Constants.KEY_TIMESTAMP
import com.pelayoness.testboda.entities.Constants.KEY_USER_NAME
import com.pelayoness.testboda.entities.Constants.SENDER_EMAIL
import com.pelayoness.testboda.entities.Constants.SENDER_PASSWORD
import com.pelayoness.testboda.entities.Question
import com.pelayoness.testboda.mail.GMailSender

class QuizResultsActivity :AppCompatActivity(){
    private var userName : String? = null
    private var userResult : Int = 0
    private var userTime : Long? = 0L
    private lateinit var questionList : ArrayList<Question>
    private var answeredList : String = ""

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
            answeredList = answeredList + question.answerSelected.toString() + ", "
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
            finishQuiz()
        }
    }

    private fun sendResultsByEmail(){
        val dialog = ProgressDialog(this)
        dialog.setTitle(getString(R.string.results_view_test_finished))
        dialog.setMessage(getString(R.string.results_view_dialog_title))
        dialog.show()
        val sender = Thread(Runnable {
            try {
                val sender = GMailSender(SENDER_EMAIL, SENDER_PASSWORD)
                sender.sendMail(getSubject(), answeredList, SENDER_EMAIL, SENDER_EMAIL)
                dialog.dismiss()
            } catch (e: Exception) {
                Log.e("mylog", "Error: " + e.message)
            }
        })
        sender.start()
    }

    private fun getSubject():String{
        return getString(R.string.results_view_text_result, userName, userResult.toString(), getUserTime())
    }

    private fun getUserTime() : String{
        val currentTime = System.currentTimeMillis() / 1000
        var spendTime = 0L
        userTime?.let {startTime ->
         spendTime = currentTime - startTime
        }
        return spendTime.toString()
    }

    private fun finishQuiz(){
        Handler().postDelayed({
            finish()
        }, 2000L)
    }
}
