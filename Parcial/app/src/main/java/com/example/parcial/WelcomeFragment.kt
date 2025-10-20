/*
Descripción: Fragmento de bienvenida con título, botón para iniciar y AlertDialog con reglas. Navega a GameFragment al hacer clic. Usa ConstraintLayout para UI clara (semana 4).
Autor: Delgado Chipana Piero Adrián
Fecha creación: 2025-10-17
Fecha última modificación: 2025-10-19
*/

package com.example.parcial

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_welcome, container, false)

        val titleTextView: TextView = view.findViewById(R.id.title_text_view)
        val startButton: Button = view.findViewById(R.id.start_button)

        // Configuramos título desde strings.xml
        titleTextView.text = getString(R.string.app_name)

        // Mostramos reglas en AlertDialog al cargar
        showRulesDialog()

        // Navegación a GameFragment
        startButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, GameFragment())
                .addToBackStack(null)
                .commit()
        }

        return view
    }

    private fun showRulesDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.welcome_title)
            .setMessage(R.string.game_rules)
            .setPositiveButton(R.string.ok) { dialog, _ -> dialog.dismiss() }
            .show()
    }
}