package com.example.testboda

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.testboda.entities.Constants
import com.example.testboda.entities.Question

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener  {

    private var currentQuestion: Int = 0
    private var questionsList: ArrayList<Question>? = null
    private var selectedOptionPosition: Int = 0
    private var correctAnswers: Int = 0
    private var userName: String? = null

    private lateinit var title : TextView
    private lateinit var answer1 : TextView
    private lateinit var answer2 : TextView
    private lateinit var answer3 : TextView
    private lateinit var answer4 : TextView
    private lateinit var proressBar : ProgressBar
    private lateinit var proressBarText : TextView
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)
        title = findViewById(R.id.questionary_view_title)
        answer1 = findViewById(R.id.questionary_view_answer_one)
        answer2 = findViewById(R.id.questionary_view_answer_two)
        answer3 = findViewById(R.id.questionary_view_answer_three)
        answer4 = findViewById(R.id.questionary_view_answer_four)
        proressBar = findViewById(R.id.questionary_view_progressbar)
        proressBarText = findViewById(R.id.questionary_view_progressbar_text)
        button = findViewById(R.id.questionary_view_button)
        initializeQuestionsList()
        initializeView()

    }

    private fun initializeQuestionsList(){
        questionsList = Constants.getQuestions()
    }

    private fun initializeView(){
        setListeners()
        setUI()

    }

    private fun setListeners(){
        answer1.setOnClickListener(this)
        answer2.setOnClickListener(this)
        answer3.setOnClickListener(this)
        answer4.setOnClickListener(this)
        button.setOnClickListener(this)
    }

    private fun setUI(){
        setTexts()
        setProgressBar()
        setButton()
    }

    private fun setTexts(){
        title.text = getString(questionsList?.get(currentQuestion)?.question?: 0)
        answer1.text = getString(questionsList?.get(currentQuestion)?.answerOne?: 0)
        answer2.text = getString(questionsList?.get(currentQuestion)?.answerTwo?: 0)
        answer3.text = getString(questionsList?.get(currentQuestion)?.answerThree?: 0)
        answer4.text = getString(questionsList?.get(currentQuestion)?.answerFour?: 0)
    }

    private fun setProgressBar(){
        proressBar.progress = currentQuestion + 1
        proressBarText.text = (currentQuestion+1).toString() + "/" +questionsList?.size
    }

    private fun setButton(){
        if(currentQuestion+1 < questionsList?.size?:0){
            button.text = getString(R.string.questionary_button_text_next)
        }else{
            button.text = getString(R.string.questionary_button_text_finish)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.questionary_view_answer_one -> onAnswerSelected(answer1, 1)
            R.id.questionary_view_answer_two -> onAnswerSelected(answer2, 2)
            R.id.questionary_view_answer_three -> onAnswerSelected(answer3, 3)
            R.id.questionary_view_answer_four -> onAnswerSelected(answer4, 4)
            R.id.questionary_view_button -> onButtonMarked()
        }
    }

    private fun onAnswerSelected(answerView: TextView, selectedOption: Int){

            defaultOptionsView()

            selectedOptionPosition = selectedOption

        answerView.setTextColor(getColor(R.color.colorText))
        answerView.setTypeface(answerView.typeface, Typeface.BOLD)
        answerView.background = ContextCompat.getDrawable(
                    this@QuizQuestionsActivity,
                    R.drawable.selected_option_border_bg
            )

    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, answer1)
        options.add(1, answer2)
        options.add(2, answer3)
        options.add(3, answer4)

        for (option in options) {
            option.setTextColor(getColor(R.color.colorHint))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                    this@QuizQuestionsActivity,
                    R.drawable.default_option_border_bg
            )
        }
    }

    private fun onButtonMarked(){

        // check if correct answert

        if(currentQuestion+1 < questionsList?.size?:0){
            currentQuestion ++

            setUI()
        }else{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }





        // go to next question



    }


}