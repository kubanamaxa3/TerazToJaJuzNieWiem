package com.example.teraztojajuzniewiem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var lista = arrayOf(findViewById<ImageButton>(R.id.btn1),findViewById<ImageButton>(R.id.btn2),findViewById<ImageButton>(R.id.btn3),findViewById<ImageButton>(R.id.btn4),findViewById<ImageButton>(R.id.btn5))
        var lista2 = arrayListOf<ImageButton>()
        var wartosc = 0


        for (elements in lista) {
            elements.setOnClickListener() {
                if (lista2.size <= 1) {
                    lista2.add(elements)
                }

                else {
                    lista2 = arrayListOf()
                    lista2.add(elements)
                }

                if (lista2.size <= 1) {
                    findViewById<TextView>(R.id.textView).text =
                        resources.getResourceEntryName(elements.id).removePrefix("btn")
                    findViewById<TextView>(R.id.textView2).text = ""
                }
                else {
                    findViewById<TextView>(R.id.textView2).text =
                        resources.getResourceEntryName(elements.id).removePrefix("btn")
                }
            }
        }
        findViewById<SeekBar>(R.id.seekBar)?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar, progress: Int,
                fromUser: Boolean
            ) {
                wartosc =((findViewById<SeekBar>(R.id.seekBar).progress - 50)/5).toString().toInt()
                findViewById<TextView>(R.id.choraWartosc).text = wartosc.toString()
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }
            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
    }
}
