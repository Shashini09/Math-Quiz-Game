package com.example.mathmentalgame

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuestionAdapter (private val dataSet:ArrayList<Question>):
    RecyclerView.Adapter<QuestionAdapter.ViewHolder>(){

    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {

        val tvProblem:TextView = view.findViewById(R.id.rvTvProblem)
        val option1:TextView = view.findViewById(R.id.rvTvOption1)
        val option2:TextView = view.findViewById(R.id.rvTvOption2)
        val option3:TextView = view.findViewById(R.id.rvTvOption3)
        val option4:TextView = view.findViewById(R.id.rvTvOption4)
        val selectedAnswer:TextView = view.findViewById(R.id.selectedAnswer)
        val correctAnswer:TextView = view.findViewById(R.id.correctAnswer)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_question_row,parent,false)
        return  ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvProblem.text = dataSet[position].problem
        holder.option1.text = dataSet[position].option1
        holder.option2.text = dataSet[position].option2
        holder.option3.text = dataSet[position].option3
        holder.option4.text = dataSet[position].option4
        holder.selectedAnswer.text = "Your Answer:${dataSet[position].selectedOption}"
        holder.correctAnswer.text = "Correct Answer:${dataSet[position].answer}"

        if(position%2 != 0){
            holder.itemView.setBackgroundColor(Color.parseColor("#000000"))
        }
        else{
            holder.itemView.setBackgroundColor(Color.parseColor("#3c3f41"))
        }
    }

}