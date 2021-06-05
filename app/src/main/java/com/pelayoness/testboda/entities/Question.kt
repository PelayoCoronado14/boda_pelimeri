package com.pelayoness.testboda.entities

import android.os.Parcel
import android.os.Parcelable

data class Question(
        val id: Int,
        val question: Int,
        val image: Int,
        val answerOne: Int,
        val answerTwo: Int,
        val answerThree: Int,
        val answerFour: Int,
        var answerSelected: Int,
        val correctAnswer: Int
):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt()) {
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(this.id)
        dest?.writeInt(this.question)
        dest?.writeInt(this.image)
        dest?.writeInt(this.answerOne)
        dest?.writeInt(this.answerTwo)
        dest?.writeInt(this.answerThree)
        dest?.writeInt(this.answerFour)
        dest?.writeInt(this.answerSelected)
        dest?.writeInt(this.correctAnswer)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Question> {
        override fun createFromParcel(parcel: Parcel): Question {
            return Question(parcel)
        }

        override fun newArray(size: Int): Array<Question?> {
            return arrayOfNulls(size)
        }
    }
}