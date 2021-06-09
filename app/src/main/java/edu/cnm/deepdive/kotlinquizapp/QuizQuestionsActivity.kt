package edu.cnm.deepdive.kotlinquizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

class QuizQuestionsActivity : AppCompatActivity() {

    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        val questionsList = Constants.getQuestions()
        Log.d("Questions size", "${questionsList.size}")

        val currentPosition = 1
        val question: Question? = questionsList[currentPosition - 1]

        val progressBar = findViewById<ProgressBar>(R.id.pb_progress_bar)
        progressBar.progress = currentPosition

        val tv_Progress = findViewById<TextView>(R.id.tv_progress)
        tv_Progress.text = "$currentPosition" + "/" + progressBar.max

        val tv_question = question!!.question

        val iv_image = findViewById<ImageView>(R.id.iv_image)
        iv_image.setImageResource(question.image)

        val tv_option_one = findViewById<TextView>(R.id.tv_option_one)
        tv_option_one.text = question.optionOne

        val tv_option_two = findViewById<TextView>(R.id.tv_option_two)
        tv_option_two.text = question.optionTwo

        val tv_option_three = findViewById<TextView>(R.id.tv_option_three)
        tv_option_three.text = question.optionThree

        val tv_option_four = findViewById<TextView>(R.id.tv_option_four)
        tv_option_four.text = question.optionFour




    }
}