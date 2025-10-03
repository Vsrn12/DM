package com.example.recyclerviewapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewapp.adapter.UsuarioAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.Toast // Import necesario

class RecyclerViewFragment : Fragment(R.layout.fragment_recycler_view) {

    private lateinit var userAdapter: UsuarioAdapter
    private lateinit var userRecyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initAdd()
    }

    private fun initAdd() {
        // ... (El código de initAdd se mantiene igual)
        val btnAgregar = requireView().findViewById<FloatingActionButton>(R.id.btnAgregar)
        btnAgregar.setOnClickListener {
            val position = userAdapter.itemCount
            val nuevoUsuario = Usuario("Usuario $position", 20, "correo$position@gmail.com", "1234")
            userAdapter.addUser(nuevoUsuario)
            userRecyclerView.scrollToPosition(position)
            Toast.makeText(requireContext(), "Usuario agregado: ${nuevoUsuario.nombre}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initRecyclerView() {
        userAdapter = UsuarioAdapter(UsuarioProvider.instance.listaUsuario)
        userRecyclerView = requireView().findViewById<RecyclerView>(R.id.recyclerViewUsuarios)
        userRecyclerView.adapter = userAdapter
        userRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        userRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

        // --- Configuración de Callbacks para Edición y Eliminación ---
        // 1. Callback para eliminación
        userAdapter.setOnUserDeleteListener { position ->
            val nombreUsuario = userAdapter.usuarios[position].nombre
            userAdapter.deleteUser(position)
            Toast.makeText(requireContext(), "Usuario '$nombreUsuario' eliminado.", Toast.LENGTH_SHORT).show()
        }

        // 2. Callback para edición
        userAdapter.setOnUserEditListener { position, updatedUser ->
            userAdapter.editUser(position, updatedUser)
            Toast.makeText(requireContext(), "Usuario '${updatedUser.nombre}' editado.", Toast.LENGTH_SHORT).show()
        }
    }
}