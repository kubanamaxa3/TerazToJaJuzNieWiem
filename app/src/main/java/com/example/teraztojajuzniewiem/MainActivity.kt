package com.example.teraztojajuzniewiem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var lista = arrayOf(findViewById<ImageButton>(R.id.btn1),findViewById<ImageButton>(R.id.btn2),findViewById<ImageButton>(R.id.btn3),findViewById<ImageButton>(R.id.btn4),findViewById<ImageButton>(R.id.btn5))
        var listaView = arrayOf<TextView>(findViewById(R.id.view12),findViewById(R.id.view13),findViewById(R.id.view14),findViewById(R.id.view15),findViewById(R.id.view21),
            findViewById(R.id.view23),findViewById(R.id.view24),findViewById(R.id.view25),findViewById(R.id.view31),findViewById(R.id.view32),findViewById(R.id.view34),findViewById(R.id.view35),
            findViewById(R.id.view41),findViewById(R.id.view42),findViewById(R.id.view43),findViewById(R.id.view45),findViewById(R.id.view51),findViewById(R.id.view52),findViewById(R.id.view53),
            findViewById(R.id.view54),)
        var lista2 = arrayListOf<ImageButton>()
        var wzorzec = "2,3"
        var wartosc = 0


        for (elements in lista) {
            elements.setOnClickListener() {
                if (lista2.size == 0) {
                    /*if(lista2.size >= 1) {
                        lista3.add(resources.getResourceEntryName(elements.id).removePrefix("btn").toInt())
                    }*/
                    lista2.add(elements)

                    findViewById<TextView>(R.id.textView).text =
                        resources.getResourceEntryName(elements.id).removePrefix("btn")
                    findViewById<TextView>(R.id.textView2).text = ""
                }

                else {
                    if(elements != lista2[0]) {
                        lista2 = arrayListOf()
                       /* lista2.add(elements) */

                       /* if(lista3.size >= 2){
                            lista3 = arrayListOf()
                            lista3.add(resources.getResourceEntryName(elements.id).removePrefix("btn").toInt())
                        }*/

                        findViewById<TextView>(R.id.textView2).text =
                            resources.getResourceEntryName(elements.id).removePrefix("btn")
                    }
                }

               /* findViewById<TextView>(R.id.textView6).text = lista3.toString() */
               /* findViewById<TextView>(R.id.textView3).text = lista2.size.toString()*/
            }

        }
        findViewById<Button>(R.id.button).setOnClickListener {
            findViewById<TextView>(R.id.textView6).text = listaView.size.toString()
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
