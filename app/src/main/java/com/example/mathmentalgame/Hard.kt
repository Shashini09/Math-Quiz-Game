package com.example.mathmentalgame

import java.lang.ArithmeticException
import kotlin.random.Random

object Hard {

    private fun makeOperation(num1:Int, num2:Int, op:String):Int
    {
        val ans:Int = try{
            when(op){
                "+" -> num1 + num2
                "-" -> num1 - num2
                "x" -> num1 * num2
                else -> num1 / num2
            }
        }catch (e: ArithmeticException){
            777777
        }

        return ans
    }

    fun getQuestions():Pair<String,Int> {

        val num1 = Random.nextInt(1, 100)
        val num2 = Random.nextInt(1, 100)
        val num3 = Random.nextInt(1, 100)
        val num4 = Random.nextInt(1, 100)
        val bracketPosition = Random.nextInt(1, 4)
        val operator1 = arrayOf("+", "-", "x", "/").random()
        val operator2 = arrayOf("+", "-", "x", "/").random()
        val operator3 = arrayOf("+", "-", "x", "/").random()

        val problem = when(bracketPosition){

            1-> "(($num1 $operator1 $num2) $operator2 $num3) $operator3 $num4"
            2-> "($num1 $operator1 $num2($operator2 $num3)) $operator3 $num4"
            else -> "($num1 $operator1 $num2) $operator2 ($num3 $operator3 $num4)"

        }

        val  halfAns1 = when(bracketPosition){
            1-> makeOperation(num1, num2, operator1)
            2-> makeOperation(num2, num3, operator2)
            else -> makeOperation(num1, num2,operator1)
        }

        val halfAns2 = when(bracketPosition){
            1-> makeOperation(halfAns1,num3,operator2)
            2-> makeOperation(num1,halfAns1,operator1)
            else-> makeOperation(num3,num4,operator3)
        }

        var answer = when (bracketPosition){
            3-> makeOperation(halfAns1, halfAns2, operator2)
            else -> makeOperation(halfAns2, num4, operator3)
        }

        if(halfAns2 == 77777777)
            answer = 77777777

        return Pair(problem, answer )

    }

}