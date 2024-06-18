package com.example.mathmentalgame

import java.lang.ArithmeticException
import kotlin.random.Random

object Easy {

    private var answer :Int =0

    fun getQuestions():Pair <String , Int>{
        val num1 = Random.nextInt(-100,100)
        val num2 = Random.nextInt(-100,100)
        val operator = arrayOf("+","-","x","/").random()

        val problem = "$num1 $operator $num2"

        answer = try{
            when(operator){
                "+" -> num1 + num2
                "-" -> num1 - num2
                "x" -> num1 * num2
                else -> num1 / num2
            }
        }catch (e:ArithmeticException){

            7777777
        }
        return Pair(problem, answer)

    }

}