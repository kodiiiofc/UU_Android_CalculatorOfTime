package com.example.calculatoroftime

import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.toColor
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var toolbarMain: Toolbar

    private lateinit var firstET: EditText
    private lateinit var secondET: EditText

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

        toolbarMain = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)
        title = "Калькулятор времени"
        toolbarMain.subtitle = "версия 2"
        toolbarMain.setLogo(R.drawable.ic_calcoftime)

        firstET = findViewById(R.id.firstET)
        secondET = findViewById(R.id.secondET)

        resultTV = findViewById(R.id.resultTV)

        plusBTN = findViewById(R.id.plusBTN)
        minusBTN = findViewById(R.id.minusBTN)

        plusBTN.setOnClickListener(this)
        minusBTN.setOnClickListener(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.resetMainMenu -> {
                firstET.text.clear()
                secondET.text.clear()
                resultTV.text = "Результат:"
                Toast.makeText(
                    applicationContext,
                    "Данные очищены",
                    Toast.LENGTH_LONG
                ).show()
                resultTV.setTextColor(resources.getColor(R.color.black))
            }

            R.id.exitMainMenu -> {
                Toast.makeText(
                    applicationContext,
                    "Приложение закрыто",
                    Toast.LENGTH_LONG
                ).show()
                finish()
            }
        }


        return super.onOptionsItemSelected(item)
    }

    override fun onClick(view: View) {

        val firstTime = TimeCalculator(firstET.text.toString())
        val secondTime = TimeCalculator(secondET.text.toString())

        resultTV.text = when (view.id) {
            R.id.plusBTN -> "Результат: " + firstTime.operation(secondTime, secondTime.sum)
            R.id.minusBTN -> "Результат: " + firstTime.operation(secondTime, secondTime.dif)
            else -> "Ошибка вычисления"
        }

        if (resultTV.text != "Ошибка вычисления") {
            resultTV.setTextColor(resources.getColor(R.color.resultColored))
        }

        Toast.makeText(
            applicationContext,
            resultTV.text,
            Toast.LENGTH_LONG
        ).show()


    }
}