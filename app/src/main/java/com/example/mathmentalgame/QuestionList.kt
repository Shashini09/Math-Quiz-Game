package com.example.mathmentalgame

import kotlin.random.Random


class QuestionList(private val questionType: String?) {

    private var questionList = ArrayList<Pair<String,Int>>(10)
    private var questionDataList = ArrayList<Question>(10)
    private var correctAnswer = ""

    private fun setQuestion(){
        when(questionType){

            "easy" ->{
                for(i in 1..10)
                    questionList.add(Easy.getQuestions())
            }

            "medium" ->{
                for(i in 1..10)
                    questionList.add(Medium.getQuestions())
            }

            else ->{
                for(i in 1..10)
                    questionList.add(Hard.getQuestions())
            }

        }
    }

    private fun getOption(position:Int):ArrayList<String>
    {

        val optionlist = ArrayList<String>(4)
        var answer = questionList[position].second
        if(answer!= 77777777){
            correctAnswer = answer.toString()
            optionlist.add(answer.toString())
            optionlist.add((answer + Random.nextInt(-9,10)).toString())
            optionlist.add((answer + Random.nextInt(-9,10)).toString())
            optionlist.add("NA")
        }
        else{
            correctAnswer = "NA"
            answer = Random.nextInt(1,4000)
            optionlist.add((answer + Random.nextInt(-9,10)).toString())
            optionlist.add((answer + Random.nextInt(-9,10)).toString())
            optionlist.add((answer + Random.nextInt(-9,10)).toString())
            optionlist.add("NA")
        }
        optionlist.shuffle()
        return optionlist
    }

    fun getQuestionList():ArrayList<Question>{
        setQuestion()
        for(i in 0..9){
            val optionList = getOption(i)
            val question = Question(
                questionList[i].first,
                correctAnswer,
                optionList[0],
                optionList[1],
                optionList[2],
                optionList[3],
                "none"

            )

            questionDataList.add(question)

        }
        return questionDataList
    }
}