package com.example.testboda.entities

data class Question(
        val id: Int,
        val question: Int,
        val image: Int,
        val answerOne: Int,
        val answerTwo: Int,
        val answerThree: Int,
        val answerFour: Int,
        val correctAnswer: Int
)