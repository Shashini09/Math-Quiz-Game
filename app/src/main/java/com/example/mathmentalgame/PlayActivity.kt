package com.example.mathmentalgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.example.mathmentalgame.databinding.ActivityPlayBinding

class PlayActivity : AppCompatActivity() {
    private var binding :ActivityPlayBinding? = null

    private var position = 0
    private var timer: CountDownTimer? = null
    private var timeGiven = 0
    private var score = 0
    private var questionDataList = ArrayList<Question>(10)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val questionType = intent.getStringExtra("questionType")
        questionDataList = QuestionList(questionType).getQuestionList()
        setGivenTime(questionType)
        updateQuestion()
        updateOption()
        updateHorizontalProgressBar()
        startTimer()

        binding?.btnOption1?.setOnClickListener{
            onSelectOption(binding?.btnOption1?.text.toString())
        }
        binding?.btnOption2?.setOnClickListener{
            onSelectOption(binding?.btnOption2?.text.toString())
        }
        binding?.btnOption3?.setOnClickListener{
            onSelectOption(binding?.btnOption3?.text.toString())
        }
        binding?.btnOption4?.setOnClickListener{
            onSelectOption(binding?.btnOption4?.text.toString())
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()


        endGame()
    }

    private fun updateQuestion(){
        binding?.tvQuestion?.text = questionDataList[position].problem

    }

    private fun updateOption(){
        binding?.btnOption1?.text = questionDataList[position].option1
        binding?.btnOption2?.text = questionDataList[position].option2
        binding?.btnOption3?.text = questionDataList[position].option3
        binding?.btnOption4?.text = questionDataList[position].option4
    }

    private fun updateHorizontalProgressBar(){
        binding?.horizontalProgressBar?.incrementProgressBy(1)
    }

    private fun setGivenTime(level:String?){

        timeGiven = when(level){
            "easy" ->10000
            "medium" ->12000
            else ->15000
        }
    }

    private fun startTimer(){

        var count = timeGiven/1000
        binding?.CircularProgressBar?.progress = timeGiven/1000
        binding?.CircularProgressBar?.max = timeGiven/1000

        timer = object : CountDownTimer(timeGiven.toLong(),1000){

            override fun onTick(p0: Long) {
                binding?.CircularProgressBar?.incrementProgressBy(-1)
                count--
                binding?.tvCountDown?.text = count.toString()
            }

            override fun onFinish() {
                setNextRound()
            }

        }.start()
    }

    private fun onSelectOption(option:String){

        if(option == questionDataList[position].answer)
            score++
        questionDataList[position].selectedOption = option
        setNextRound()
    }

    private fun setNextRound(){

        if(position<9){
            position++
            timer?.cancel()
            timer = null
            updateHorizontalProgressBar()
            updateQuestion()
            updateOption()
            startTimer()
        }
        else
            endGame()
    }

    private fun endGame(){

        val intent = Intent(this,FinishActivity::class.java)
        intent.putExtra("score",score)
        intent.putExtra("dataSet",questionDataList)
        startActivity(intent)
        finish()
        timer?.cancel()
        timer = null
        binding = null
    }


}