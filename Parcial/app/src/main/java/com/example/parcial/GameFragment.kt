/*
Descripción: Fragmento del juego con un View para color aleatorio, botones para elegir, temporizador de 30s con CountDownTimer, y TextViews para puntaje y tiempo. Suma puntos al acertar y navega a ResultFragment al terminar (semana 6).
Autor: Delgado Chipana Piero Adrián
Fecha creación: 2025-10-17
Fecha última modificación: 2025-10-19
*/

package com.example.parcial

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.util.Random

class GameFragment : Fragment() {

    private lateinit var colorView: View
    private lateinit var scoreTextView: TextView
    private lateinit var timeTextView: TextView
    private var currentColor: Int = 0
    private var score: Int = 0
    private lateinit var timer: CountDownTimer
    private val colors = listOf(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game, container, false)

        colorView = view.findViewById(R.id.color_view)
        scoreTextView = view.findViewById(R.id.score_text_view)
        timeTextView = view.findViewById(R.id.time_text_view)

        val redButton: Button = view.findViewById(R.id.red_button)
        val greenButton: Button = view.findViewById(R.id.green_button)
        val blueButton: Button = view.findViewById(R.id.blue_button)
        val yellowButton: Button = view.findViewById(R.id.yellow_button)

        // Configuramos listeners para botones
        redButton.setOnClickListener { checkAnswer(Color.RED) }
        greenButton.setOnClickListener { checkAnswer(Color.GREEN) }
        blueButton.setOnClickListener { checkAnswer(Color.BLUE) }
        yellowButton.setOnClickListener { checkAnswer(Color.YELLOW) }

        // Iniciamos el juego
        startGame()

        return view
    }

    private fun startGame() {
        score = 0
        updateScore()
        generateRandomColor()

        // Temporizador de 30 segundos
        timer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeTextView.text = getString(R.string.time_remaining, millisUntilFinished / 1000)
            }

            override fun onFinish() {
                timeTextView.text = getString(R.string.time_up)
                navigateToResult()
            }
        }.start()
    }

    private fun generateRandomColor() {
        val random = Random()
        currentColor = colors[random.nextInt(colors.size)]
        colorView.setBackgroundColor(currentColor)
    }

    private fun checkAnswer(selectedColor: Int) {
        if (selectedColor == currentColor) {
            score++
            updateScore()
            generateRandomColor()
            Toast.makeText(requireContext(), R.string.correct, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), R.string.incorrect, Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateScore() {
        scoreTextView.text = getString(R.string.current_score, score)
    }

    private fun navigateToResult() {
        val bundle = Bundle().apply { putInt("score", score) }
        val resultFragment = ResultFragment().apply { arguments = bundle }
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, resultFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        timer.cancel() // Evitamos memory leaks
    }
}