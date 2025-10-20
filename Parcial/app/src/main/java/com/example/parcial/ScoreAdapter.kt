/*
Descripción: Adapter para RecyclerView que muestra el historial de puntajes. Usa layout simple de Android para mostrar cada puntaje en un TextView (semana 6).
Autor: Delgado Chipana Piero Adrián
Fecha creación: 2025-10-17
Fecha última modificación: 2025-10-19
*/

package com.example.parcial

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ScoreAdapter(private val scores: List<Int>) : RecyclerView.Adapter<ScoreAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val scoreTextView: TextView = view.findViewById(android.R.id.text1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.scoreTextView.text = scores[position].toString()
    }

    override fun getItemCount(): Int = scores.size
}