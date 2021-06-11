package edu.cnm.deepdive.kotlinquizapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.provider.SyncStateContract
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import edu.cnm.deepdive.kotlinquizapp.Constants
import edu.cnm.deepdive.kotlinquizapp.Question
import edu.cnm.deepdive.kotlinquizapp.R


class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1 // Default and the first question position
    private var mQuestionsList: ArrayList<Question>? = null

    private var mSelectedOptionPosition: Int = 0

    /**
     * This function is auto created by Android when the Activity Class is created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor
        super.onCreate(savedInstanceState)
        // This is used to align the xml view to this class
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

        // TODO(STEP 1: Adding a click event for submit button.)
        // START
        val btn_submit = findViewById<Button>(R.id.bt_submit)
        btn_submit.setOnClickListener(this)
        // END
    }

    override fun onClick(v: View?) {

        val bt_submit = findViewById<Button>(R.id.bt_submit)
        
        
        when (v?.id) {

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

            // TODO(STEP 2: Adding a click event for submit button. And change the questions and check the selected answers.)
            // START


            R.id.bt_submit -> {

                if (mSelectedOptionPosition == 0) {

                    mCurrentPosition++

                    when {

                        mCurrentPosition <= mQuestionsList!!.size -> {

                            setQuestion()
                        }
                        else -> {

                            Toast.makeText(this@QuizQuestionsActivity, "You have successfully completed the quiz.", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    val question = mQuestionsList?.get(mCurrentPosition - 1)

                    // This is to check if the answer is wrong
                    if (question!!.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }

                    // This is for correct answer
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if (mCurrentPosition == mQuestionsList!!.size) {
                        bt_submit.text = "FINISH"
                    } else {
                        bt_submit.text = "GO TO NEXT QUESTION"
                    }

                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    /**
     * A function for setting the question to UI components.
     */
    @SuppressLint("WrongViewCast")
    private fun setQuestion() {

        val question = mQuestionsList!!.get(mCurrentPosition - 1) // Getting the question from the list with the help of current position.

        defaultOptionsView()

        // TODO (STEP 6: Check here if the position of question is last then change the text of the button.)
        // START
        if (mCurrentPosition == mQuestionsList!!.size) {
            val btn_submit = findViewById<Button>(R.id.bt_submit)
            btn_submit.text = "FINISH"
        } else {
            val btn_submit = findViewById<Button>(R.id.bt_submit)
            btn_submit.text = "SUBMIT"
        }
        // END
        val progressBar = findViewById<ProgressBar>(R.id.tv_progress)
        progressBar.progress = mCurrentPosition

        val progressText = findViewById<TextView>(R.id.tv_progress)
        progressText.text = "$mCurrentPosition" + "/" + progressBar.getMax()

        val tv_question = findViewById<TextView>(R.id.tv_question)
        tv_question.text = question.question
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

    /**
     * A function to set the view of selected option view.
     */
    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {

        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(
            Color.parseColor("#363A43")
        )
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this@QuizQuestionsActivity,
            R.drawable.selected_option_border_bg
        )
    }

    /**
     * A function to set default options view when the new question is loaded or when the answer is reselected.
     */
    private fun defaultOptionsView() {

        val options = ArrayList<TextView>()
        val tv_option_one = findViewById<TextView>(R.id.tv_option_one)
        options.add(0, tv_option_one)
        val tv_option_two = findViewById<TextView>(R.id.tv_option_two)
        options.add(1, tv_option_two)
        val tv_option_three = findViewById<TextView>(R.id.tv_option_three)
        options.add(2, tv_option_three)
        val tv_option_four = findViewById<TextView>(R.id.tv_option_four)
        options.add(3, tv_option_four)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this@QuizQuestionsActivity,
                R.drawable.default_option_border_bg
            )
        }
    }

    // TODO (STEP 3: Create a function for answer view.)
    // START
    /**
     * A function for answer view which is used to highlight the answer is wrong or right.
     */
    private fun answerView(answer: Int, drawableView: Int) {

        when (answer) {

            1 -> {
                val tv_option_one = findViewById<TextView>(R.id.tv_option_one)
                tv_option_one.background = ContextCompat.getDrawable(
                    this@QuizQuestionsActivity,
                    drawableView
                )
            }
            2 -> {
                val tv_option_two = findViewById<TextView>(R.id.tv_option_two)
                tv_option_two.background = ContextCompat.getDrawable(
                    this@QuizQuestionsActivity,
                    drawableView
                )
            }
            3 -> {
                val tv_option_three = findViewById<TextView>(R.id.tv_option_three)
                tv_option_three.background = ContextCompat.getDrawable(
                    this@QuizQuestionsActivity,
                    drawableView
                )
            }
            4 -> {
                val tv_option_four = findViewById<TextView>(R.id.tv_option_four)
                tv_option_four.background = ContextCompat.getDrawable(
                    this@QuizQuestionsActivity,
                    drawableView
                )
            }
        }
    }
    // END
}