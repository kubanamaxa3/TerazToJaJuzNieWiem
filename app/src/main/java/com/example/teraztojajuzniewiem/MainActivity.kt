package com.example.teraztojajuzniewiem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var lista = arrayOf(findViewById<ImageButton>(R.id.btn1),findViewById<ImageButton>(R.id.btn2),findViewById<ImageButton>(R.id.btn3),findViewById<ImageButton>(R.id.btn4),findViewById<ImageButton>(R.id.btn5))
    }
}