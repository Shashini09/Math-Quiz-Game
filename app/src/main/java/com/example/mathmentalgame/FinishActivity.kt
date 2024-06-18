package com.example.mathmentalgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FinishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        val score = intent.getIntExtra("score",0)
        val data:ArrayList<Question> = intent.getSerializableExtra("dataSet") as ArrayList<Question>

        val tvScore:TextView = findViewById(R.id.tvScore)
        tvScore.text = "Your Score\n$score/10"

        setAdapterRecyclerView(data)

        val btnHome:Button = findViewById(R.id.btnHome)
        btnHome.setOnClickListener{finish()}

    }

    private fun setAdapterRecyclerView(data:ArrayList<Question>){
        val recyclerView:RecyclerView = findViewById(R.id.rvQuestionList)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = QuestionAdapter(data)
        recyclerView.adapter = adapter
    }
}