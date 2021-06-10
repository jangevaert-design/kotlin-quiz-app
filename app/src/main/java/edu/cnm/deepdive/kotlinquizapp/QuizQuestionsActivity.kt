package edu.cnm.deepdive.kotlinquizapp

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mQuestionsList = Constants.getQuestions()

        setQuestion()
        val tv_option_one = findViewById<TextView>(R.id.tv_option_one)
        tv_option_one.setOnClickListener(this)
        val tv_option_two = findViewById<TextView>(R.id.tv_option_two)
        tv_option_two.setOnClickListener(this)
        val tv_option_three = findViewById<TextView>(R.id.tv_option_three)
        tv_option_three.setOnClickListener(this)
        val tv_option_four = findViewById<TextView>(R.id.tv_option_four)
        tv_option_four.setOnClickListener(this)


    }

    private fun setQuestion() {

        mCurrentPosition = 1
        val question = mQuestionsList!![mCurrentPosition - 1]

        defaultOptionsView()

        val progressBar = findViewById<ProgressBar>(R.id.pb_progress_bar)
        progressBar.progress = mCurrentPosition

        val tv_Progress = findViewById<TextView>(R.id.tv_progress)
        tv_Progress.text = "$mCurrentPosition" + "/" + progressBar.max

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

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()

        val tv_option_one = findViewById<TextView>(R.id.tv_option_one)
        options.add(0, tv_option_one)

        val tv_option_two = findViewById<TextView>(R.id.tv_option_two)
        options.add(0, tv_option_two)
        val tv_option_three = findViewById<TextView>(R.id.tv_option_three)
        options.add(0, tv_option_three)
        val tv_option_four = findViewById<TextView>(R.id.tv_option_four)
        options.add(0, tv_option_four)

        for (option in options) {
            option.setTextColor(Color.parseColor("7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.tv_option_one -> {
                val tv_option_one = findViewById<TextView>(R.id.tv_option_one)
                selectedOptionView(tv_option_one, 1)
            }
            R.id.tv_option_two -> {
                val tv_option_two = findViewById<TextView>(R.id.tv_option_two)
                selectedOptionView(tv_option_two, 2)
            }
            R.id.tv_option_three -> {
                val tv_option_three = findViewById<TextView>(R.id.tv_option_three)
                selectedOptionView(tv_option_three, 3)
            }
            R.id.tv_option_four -> {
                val tv_option_four = findViewById<TextView>(R.id.tv_option_four)
                selectedOptionView(tv_option_four, 4)
            }
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNumber: Int ) {

        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNumber

        tv.setTextColor(Color.parseColor("363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD_ITALIC)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)

    }
}