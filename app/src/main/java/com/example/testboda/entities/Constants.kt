package com.example.testboda.entities

import com.example.testboda.R

object Constants {
    const val KEY_QUESTION_LIST: String = "key_question_list"
    const val KEY_USER_NAME: String = "key_user_name"
    const val KEY_TIMESTAMP: String = "key_timestamp"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWER: String = "correct_answers"

    fun getQuestions(): ArrayList<Question> {
        val questionList = ArrayList<Question>()

        // 1
        val que1 = Question(
            1,
            R.string.questionary_question_1_title,
            0,
            R.string.questionary_question_1_answer_1,
            R.string.questionary_question_1_answer_2,
            R.string.questionary_question_1_answer_3,
            R.string.questionary_question_1_answer_4,
            0,
            2
        )

        questionList.add(que1)

        // 2
        val que2 = Question(
            2,
            R.string.questionary_question_2_title,
            0,
            R.string.questionary_question_2_answer_1,
            R.string.questionary_question_2_answer_2,
            R.string.questionary_question_2_answer_3,
            R.string.questionary_question_2_answer_4,
            0,
                1
        )

        questionList.add(que2)

        // 3
        val que3 = Question(
            3,
            R.string.questionary_question_3_title,
            0,
            R.string.questionary_question_3_answer_1,
            R.string.questionary_question_3_answer_2,
            R.string.questionary_question_3_answer_3,
            R.string.questionary_question_3_answer_4,
            0,
                0
        )

        questionList.add(que3)

        // 4
        val que4 = Question(
            4,
            R.string.questionary_question_4_title,
            0,
            R.string.questionary_question_4_answer_1,
            R.string.questionary_question_4_answer_2,
            R.string.questionary_question_4_answer_3,
            R.string.questionary_question_4_answer_4,
                0,
                0
        )

        questionList.add(que4)

        // 5
        val que5 = Question(
            5,
            R.string.questionary_question_5_title,
            0,
            R.string.questionary_question_5_answer_1,
            R.string.questionary_question_5_answer_2,
            R.string.questionary_question_5_answer_3,
            R.string.questionary_question_5_answer_4,
                0,
                0
        )

        questionList.add(que5)

        // 6
        val que6 = Question(
            6,
            R.string.questionary_question_6_title,
            0,
            R.string.questionary_question_6_answer_1,
            R.string.questionary_question_6_answer_2,
            R.string.questionary_question_6_answer_3,
            R.string.questionary_question_6_answer_4,
                0,
                0
        )

        questionList.add(que6)

        // 7
        val que7 = Question(
            7,
            R.string.questionary_question_7_title,
            0,
            R.string.questionary_question_7_answer_1,
            R.string.questionary_question_7_answer_2,
            R.string.questionary_question_7_answer_3,
            R.string.questionary_question_7_answer_4,
                0,
                0
        )

        questionList.add(que7)

        // 8
        val que8 = Question(
            8,
            R.string.questionary_question_8_title,
            0,
            R.string.questionary_question_8_answer_1,
            R.string.questionary_question_8_answer_2,
            R.string.questionary_question_8_answer_3,
            R.string.questionary_question_8_answer_4,
                0,
                0
        )

        questionList.add(que8)

        // 9
        val que9 = Question(
            9,
            R.string.questionary_question_9_title,
            0,
            R.string.questionary_question_9_answer_1,
            R.string.questionary_question_9_answer_2,
            R.string.questionary_question_9_answer_3,
            R.string.questionary_question_9_answer_4,
                0,
                0
        )

        questionList.add(que9)

        // 10
        val que10 = Question(
            10,
            R.string.questionary_question_10_title,
            0,
            R.string.questionary_question_10_answer_1,
            R.string.questionary_question_10_answer_2,
            R.string.questionary_question_10_answer_3,
            R.string.questionary_question_10_answer_4,
                0,
                0
        )

        questionList.add(que10)

        return questionList
    }
}