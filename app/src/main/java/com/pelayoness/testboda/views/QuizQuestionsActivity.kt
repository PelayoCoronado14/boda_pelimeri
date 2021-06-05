package com.pelayoness.testboda.views

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.pelayoness.testboda.R
import com.pelayoness.testboda.entities.Constants
import com.pelayoness.testboda.entities.Constants.KEY_QUESTION_LIST
import com.pelayoness.testboda.entities.Constants.KEY_TIMESTAMP
import com.pelayoness.testboda.entities.Constants.KEY_USER_NAME
import com.pelayoness.testboda.entities.Question

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener  {

    companion object{
        private const val FIRST_QUESTION = 1
    }

    private var currentQuestion: Int = 1
    private var userName: String? = null
    private var userTime: Long? = null

    private lateinit var questionList: ArrayList<Question>
    private lateinit var title : TextView
    private lateinit var answer1 : TextView
    private lateinit var answer2 : TextView
    private lateinit var answer3 : TextView
    private lateinit var answer4 : TextView
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)
        title = findViewById(R.id.questionary_view_title)
        answer1 = findViewById(R.id.questionary_view_answer_one)
        answer2 = findViewById(R.id.questionary_view_answer_two)
        answer3 = findViewById(R.id.questionary_view_answer_three)
        answer4 = findViewById(R.id.questionary_view_answer_four)
        button = findViewById(R.id.questionary_view_button)
        getExtras()
        initializeQuestionList()
        initializeView()

    }

    override fun onBackPressed() {
        if(currentQuestion > FIRST_QUESTION){
            currentQuestion --
            setUI()
        } else {
            return
        }
    }

    private fun getExtras(){
        intent.extras.let {
            userName = it?.getString(KEY_USER_NAME)
            userTime= it?.getLong(KEY_TIMESTAMP)
        }
    }

    private fun initializeQuestionList(){
        questionList = Constants.getQuestions()
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
        resetPreviousSelection()
        setTexts()
        setProgressBar()
        setButton()
        setAnswerIfPreviouslySelected()
    }

    private fun setTexts(){
        title.text = getString(questionList[currentQuestion-1].question)
        answer1.text = getString(questionList[currentQuestion-1].answerOne)
        answer2.text = getString(questionList[currentQuestion-1].answerTwo)
        answer3.text = getString(questionList[currentQuestion-1].answerThree)
        answer4.text = getString(questionList[currentQuestion-1].answerFour)
    }

    private fun setProgressBar(){
        findViewById<ProgressBar>(R.id.questionary_view_progressbar).progress = currentQuestion
        findViewById<TextView>(R.id.questionary_view_progressbar_text).text = (currentQuestion).toString() + "/" +questionList.size
    }

    private fun setButton(){
        if(currentQuestion < questionList.size){
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
        resetPreviousSelection()
        selectAnswer(answerView)
        questionList[currentQuestion-1].answerSelected = selectedOption
    }

    private fun resetPreviousSelection() {
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

    private fun setAnswerIfPreviouslySelected(){
        when(questionList[currentQuestion-1].answerSelected){
            0 -> return
            1 -> selectAnswer(answer1)
            2 -> selectAnswer(answer2)
            3 -> selectAnswer(answer3)
            4 -> selectAnswer(answer4)

        }
    }

    private fun selectAnswer(answerView: TextView){
        answerView.setTextColor(getColor(R.color.colorText))
        answerView.setTypeface(answerView.typeface, Typeface.BOLD)
        answerView.background = ContextCompat.getDrawable(
                this@QuizQuestionsActivity,
                R.drawable.selected_option_border_bg)
    }

    private fun onButtonMarked(){
        if(isLastQuestion()){
            val intent = Intent(this, QuizResultsActivity::class.java)
            intent.putExtra(KEY_USER_NAME, userName)
            intent.putExtra(KEY_TIMESTAMP, userTime)
            intent.putExtra(KEY_QUESTION_LIST, questionList)
            startActivity(intent)
            finish()
        }else{
            currentQuestion ++
            resetPreviousSelection()
            setUI()
        }
    }

    private fun isLastQuestion() : Boolean{
        return currentQuestion >= questionList?.size?:0
    }
}