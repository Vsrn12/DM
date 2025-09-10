/*
 * Descripción: Reproductor de música básico con funciones de reproducir,
 * pausar y detener usando MediaPlayer y archivos en res/raw
 * Autor: Delgado Chipana Piero Adrián
 * Fecha creación: 11/09/2025
 * Fecha última modificación: 11/09/2025
 */

package com.example.practica3

import android.app.Activity
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {

    private var mediaPlayer: MediaPlayer? = null
    private var isPlaying = false
    private var isPaused = false

    private lateinit var btnPlay: Button
    private lateinit var btnPause: Button
    private lateinit var btnStop: Button
    private lateinit var tvStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        setupListeners()
        initializeMediaPlayer()
    }

    private fun initializeViews() {
        btnPlay = findViewById(R.id.btnPlay)
        btnPause = findViewById(R.id.btnPause)
        btnStop = findViewById(R.id.btnStop)
        tvStatus = findViewById(R.id.tvStatus)
    }

    private fun setupListeners() {
        btnPlay.setOnClickListener {
            playMusic()
        }

        btnPause.setOnClickListener {
            pauseMusic()
        }

        btnStop.setOnClickListener {
            stopMusic()
        }
    }

    private fun initializeMediaPlayer() {
        try {
            mediaPlayer = MediaPlayer.create(this, R.raw.diganle)
            mediaPlayer?.setOnCompletionListener {
                stopMusic()
                Toast.makeText(this, "Terminaste de llorar?", Toast.LENGTH_SHORT).show()
            }
            updateStatus("Listo para llorar")
        } catch (e: Exception) {
            Toast.makeText(this, "Error al cargar el archivo de audio", Toast.LENGTH_LONG).show()
            updateStatus("Error: Archivo no encontrado")
        }
    }

    private fun playMusic() {
        try {
            mediaPlayer?.let { player ->
                if (isPaused) {
                    player.start()
                    isPaused = false
                    isPlaying = true
                    updateStatus("Reproduciendo...")
                    Toast.makeText(this, "Musica reanudada", Toast.LENGTH_SHORT).show()
                } else if (!isPlaying) {
                    player.start()
                    isPlaying = true
                    updateStatus("Reproduciendo...")
                    Toast.makeText(this, "Musica iniciada", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Ya se esta reproduciendo", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Error al reproducir", Toast.LENGTH_SHORT).show()
        }
    }

    private fun pauseMusic() {
        try {
            mediaPlayer?.let { player ->
                if (isPlaying && player.isPlaying) {
                    player.pause()
                    isPaused = true
                    isPlaying = false
                    updateStatus("Pausado")
                    Toast.makeText(this, "Musica pausada", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "No hay musica reproduciendose", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Error al pausar", Toast.LENGTH_SHORT).show()
        }
    }

    private fun stopMusic() {
        try {
            mediaPlayer?.let { player ->
                if (isPlaying || isPaused) {
                    player.stop()
                    player.prepare()
                    isPlaying = false
                    isPaused = false
                    updateStatus("Detenido")
                    Toast.makeText(this, "Musica detenida", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "No hay musica reproduciendose", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Error al detener", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateStatus(status: String) {
        tvStatus.text = "Estado: $status"
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.apply {
            if (isPlaying) {
                stop()
            }
            release()
        }
        mediaPlayer = null
    }

    override fun onPause() {
        super.onPause()
        if (isPlaying) {
            pauseMusic()
        }
    }
}