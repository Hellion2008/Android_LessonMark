package com.example.android_lessonmark

import android.graphics.Color
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var editText: EditText

    private lateinit var button: Button

    private var isButtonClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        editText = findViewById(R.id.textET)
        registerForContextMenu(editText)

        button = findViewById(R.id.buttonBTN)
        button.setOnClickListener(this)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.cm_change_color -> {
                if (isButtonClicked){
                    when(editText.text.toString().toInt()){
                        in 1..10 -> editText.setBackgroundColor(Color.RED)
                        in 11..20 -> editText.setBackgroundColor(Color.parseColor("#FF8000"))
                        in 21..30 -> editText.setBackgroundColor(Color.YELLOW)
                        in 31..40 -> editText.setBackgroundColor(Color.GREEN)
                        in 41..50 -> editText.setBackgroundColor(Color.BLUE)
                    }
                    isButtonClicked = false
                } else{
                    when(editText.text.toString()){
                        "1" -> editText.setBackgroundColor(Color.parseColor("#FF8000"))
                        "2" -> editText.setBackgroundColor(Color.YELLOW)
                        "3" -> editText.setBackgroundColor(Color.GREEN)
                        "4" -> editText.setBackgroundColor(Color.BLUE)
                        "5" -> editText.setBackgroundColor(Color.RED)
                    }
                }

                Toast.makeText(this, "Цвет изменен", Toast.LENGTH_LONG).show()
            }
            R.id.cm_exit -> {
                Toast.makeText(this, "Работа завершена", Toast.LENGTH_LONG).show()
                finish()

            }
            else -> return super.onContextItemSelected(item)
        }
        return true
    }

    override fun onClick(p0: View) {
        editText.setText("${(Math.random() * 50).toInt()}")
        isButtonClicked = true
    }
}