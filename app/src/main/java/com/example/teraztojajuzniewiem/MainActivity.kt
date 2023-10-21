package com.example.teraztojajuzniewiem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView
import java.util.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    // Klasa reprezentująca krawędź grafu wraz z wagą
    data class Edge(val to: Int, val weight: Int)

    // Klasa reprezentująca wierzchołek grafu
    data class Vertex(val id: Int) {
        val neighbors = mutableListOf<Edge>()
    }

    // Klasa reprezentująca graf skierowany ważony
    class WeightedDirectedGraph {
        val vertices = mutableMapOf<Int, Vertex>()

        fun addVertex(id: Int) {
            if (!vertices.containsKey(id)) {
                vertices[id] = Vertex(id)
            }
        }

        fun addEdge(from: Int, to: Int, weight: Int) {
            val fromVertex = vertices[from]
            val toVertex = vertices[to]

            if (fromVertex != null && toVertex != null) {
                fromVertex.neighbors.add(Edge(to, weight))
            } else {
                throw IllegalArgumentException("Wierzchołki 'from' i 'to' muszą istnieć w grafie.")
            }
        }

        fun removeEdge(from: Int, to: Int) {
            val fromVertex = vertices[from]
            fromVertex?.neighbors?.removeIf { it.to == to }
        }

        fun getNeighbors(id: Int): List<Edge> {
            val vertex = vertices[id]
            return vertex?.neighbors ?: emptyList()
        }


        fun floydWarshall(): Array<IntArray> {
            val n = vertices.size
            val dist = Array(n) { IntArray(n) { Int.MAX_VALUE } }

            // Inicjalizacja macierzy odległości
            for (i in 0 until n) {
                dist[i][i] = 0
                val vertex = vertices[i]
                for (edge in vertex!!.neighbors) {
                    dist[i][edge.to] = edge.weight
                }
            }

            // Algorytm Floyd-Warshalla
            for (k in 0 until n) {
                for (i in 0 until n) {
                    for (j in 0 until n) {
                        if (dist[i][k] != Int.MAX_VALUE && dist[k][j] != Int.MAX_VALUE &&
                            dist[i][k] + dist[k][j] < dist[i][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j]
                        }
                    }
                }
            }

            return dist
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var lista = arrayOf(
            findViewById<ImageButton>(R.id.btn1),
            findViewById<ImageButton>(R.id.btn2),
            findViewById<ImageButton>(R.id.btn3),
            findViewById<ImageButton>(R.id.btn4),
            findViewById<ImageButton>(R.id.btn5)
        )
        var listaView = arrayOf<TextView>(
            findViewById(R.id.view12),
            findViewById(R.id.view13),
            findViewById(R.id.view14),
            findViewById(R.id.view15),
            findViewById(R.id.view21),
            findViewById(R.id.view23),
            findViewById(R.id.view24),
            findViewById(R.id.view25),
            findViewById(R.id.view31),
            findViewById(R.id.view32),
            findViewById(R.id.view34),
            findViewById(R.id.view35),
            findViewById(R.id.view41),
            findViewById(R.id.view42),
            findViewById(R.id.view43),
            findViewById(R.id.view45),
            findViewById(R.id.view51),
            findViewById(R.id.view52),
            findViewById(R.id.view53),
            findViewById(R.id.view54),
        )
        var lista2 = arrayListOf<ImageButton>()
        var wzorzec = ""
        var wartosc = 0
        val graph = WeightedDirectedGraph()
        graph.addVertex(1)
        graph.addVertex(2)
        graph.addVertex(3)
        graph.addVertex(4)
        graph.addVertex(5)

        // ta część jest z lenistwa
        for (elements in listaView) {
            var view = resources.getResourceEntryName(elements.id).removePrefix("view")
            elements.text = "0"
            elements.textSize = 20.toFloat()
            /* graph.addEdge(view[0].toString().toInt(),view[1].toString().toInt(),0) */
        }


        findViewById<TextView>(R.id.textView6).text = wzorzec
        findViewById<TextView>(R.id.textView3).text = lista2.size.toString()
        for (elements in lista) {
            elements.setOnClickListener() {
                if (lista2.size == 0) {
                    if (wzorzec != "") {
                        wzorzec = ""
                        wzorzec += resources.getResourceEntryName(elements.id).removePrefix("btn")
                    } else {
                        wzorzec += resources.getResourceEntryName(elements.id).removePrefix("btn")
                    }
                    lista2.add(elements)

                    findViewById<TextView>(R.id.textView).text =
                        resources.getResourceEntryName(elements.id).removePrefix("btn")
                    findViewById<TextView>(R.id.textView2).text = ""

                    findViewById<TextView>(R.id.textView6).text = wzorzec
                    findViewById<TextView>(R.id.textView3).text = lista2.size.toString()
                } else {
                    if (elements != lista2[0]) {
                        lista2 = arrayListOf()

                        wzorzec += resources.getResourceEntryName(elements.id).removePrefix("btn")
                            .toInt()
                        findViewById<TextView>(R.id.textView2).text =
                            resources.getResourceEntryName(elements.id).removePrefix("btn")

                        findViewById<TextView>(R.id.textView6).text = wzorzec
                        findViewById<TextView>(R.id.textView3).text = lista2.size.toString()
                    }
                }
                findViewById<TextView>(R.id.textView6).text = wzorzec
                findViewById<TextView>(R.id.textView3).text = lista2.size.toString()

            }

        }
        findViewById<Button>(R.id.button).setOnClickListener {
            findViewById<TextView>(R.id.textView6).text = listaView.size.toString()
        }
        findViewById<SeekBar>(R.id.seekBar)?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar, progress: Int,
                fromUser: Boolean
            ) {
                wartosc =
                    ((findViewById<SeekBar>(R.id.seekBar).progress - 50) / 5).toString().toInt()
                findViewById<TextView>(R.id.choraWartosc).text = wartosc.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
        findViewById<Button>(R.id.button2).setOnClickListener {
            for (elements in listaView) {
                var view = resources.getResourceEntryName(elements.id).removePrefix("view")
                if (view == wzorzec) {
                    elements.text = wartosc.toString()
                    graph.removeEdge(view[0].toString().toInt(),view[1].toString().toInt())
                    graph.addEdge(view[0].toString().toInt(), view[1].toString().toInt(), wartosc)
                }
            }
        }


        findViewById<Button>(R.id.button).setOnClickListener {
            for (elements in listaView) {
                elements.text = "0"
                var view = resources.getResourceEntryName(elements.id).removePrefix("view")
                 graph.removeEdge(view[0].toString().toInt(),view[1].toString().toInt())
            }
        }


        findViewById<Button>(R.id.button3).setOnClickListener {
           /* val shortestDistances = graph.floydWarshall()
            val startVertex = 1
            val endVertex = 3
            val shortestDistance = shortestDistances[startVertex][endVertex]

            if (shortestDistance != Int.MAX_VALUE) {
                findViewById<TextView>(R.id.textView7).text=("Najkrótsza ścieżka między $startVertex i $endVertex wynosi $shortestDistance")
            } else {
                findViewById<TextView>(R.id.textView7).text=("Brak ścieżki między $startVertex i $endVertex")
            }*/
        }
        findViewById<Button>(R.id.buttonTest).setOnClickListener {
        for (elements in listaView) {
            val random = Random.nextInt(-10, 11)
            var view = resources.getResourceEntryName(elements.id).removePrefix("view")
            elements.text = random.toString()
            graph.addEdge(view[0].toString().toInt(), view[1].toString().toInt(), random)
            }
        }
    }
}

