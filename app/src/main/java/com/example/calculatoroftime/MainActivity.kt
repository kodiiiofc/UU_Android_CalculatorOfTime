package com.example.calculatoroftime

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var fromET : EditText
    private lateinit var toET : EditText

    private lateinit var plusBTN: Button
    private lateinit var minusBTN: Button

    private lateinit var resultTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fromET = findViewById(R.id.fromET)
        toET = findViewById(R.id.toET)

        resultTV = findViewById(R.id.resultTV)

        plusBTN = findViewById(R.id.plusBTN)
        minusBTN = findViewById(R.id.minusBTN)

        plusBTN.setOnClickListener(this)
        minusBTN.setOnClickListener(this)

    }

    override fun onClick(view: View) {

        val timeFrom = TimeCalculator(fromET.text.toString())
        val timeTo = TimeCalculator(toET.text.toString())

        try {
            resultTV.text = when (view.id) {
                R.id.plusBTN -> timeFrom.operation(timeTo, timeTo.sum)
                R.id.minusBTN -> timeFrom.operation(timeTo, timeTo.dif)
                else -> "Ошибка вычисления"
            }
        } catch (e: Exception) {
            resultTV.text = "Что-то пошло не так"
        }

    }


}