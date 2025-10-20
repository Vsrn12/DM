/*
Descripción: Muestra puntaje actual (de bundle), max score de Room, historial en RecyclerView (de Room), y botón para replay. Inserta nuevo puntaje en Room. Usa corutinas para operaciones asíncronas (semana 7).
Autor: Delgado Chipana Piero Adrián
Fecha creación: 2025-10-17
Fecha última modificación: 2025-10-19
*/

package com.example.parcial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ResultFragment : Fragment() {

    private var currentScore: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_result, container, false)

        currentScore = arguments?.getInt("score") ?: 0

        val currentScoreTextView: TextView = view.findViewById(R.id.current_score_text_view)
        val highScoreTextView: TextView = view.findViewById(R.id.high_score_text_view)
        val historyRecyclerView: RecyclerView = view.findViewById(R.id.history_recycler_view)
        val replayButton: Button = view.findViewById(R.id.replay_button)

        currentScoreTextView.text = getString(R.string.final_score, currentScore)

        // Operaciones Room con corutinas
        CoroutineScope(Dispatchers.IO).launch {
            val dao = MainActivity.database.scoreDao()
            dao.insert(ScoreEntity(score = currentScore))

            val maxScore = dao.getMaxScore() ?: 0
            val allScores = dao.getAllScores()

            withContext(Dispatchers.Main) {
                highScoreTextView.text = getString(R.string.high_score, maxScore)
                historyRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                historyRecyclerView.adapter = ScoreAdapter(allScores.map { it.score })
            }
        }

        // Replay navega a GameFragment
        replayButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, GameFragment())
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}