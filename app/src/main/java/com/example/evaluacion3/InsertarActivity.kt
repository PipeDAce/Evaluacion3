package com.example.evaluacion3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.evaluacion3.Models.Dispositivo
import com.example.evaluacion3.databinding.ActivityInsertarBinding
import com.example.evaluacion3.vistas.HomeFragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.FirebaseDatabaseKtxRegistrar

class InsertarActivity : AppCompatActivity() {

    //ViewBindind
    private lateinit var binding: ActivityInsertarBinding
    // FireBase Database
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicio de base de datos y ruta de almacenamiento
        database = FirebaseDatabase.getInstance().getReference( "Dispositivos")

        // Obtener datos e insertar
        binding.btnGuardar.setOnClickListener {
            // Obtener los datos del formulario
            val nombre = binding.etNombreDispositivo.text.toString()
            // Gererar el id de forma que el dispositivo sea único
            val id = database.child("Dispositivos").push().key

            if(nombre.isEmpty()){
                binding.etNombreDispositivo.error = "Por Favor Ingrese Nombre"
                return@setOnClickListener
            }

            val dispositivo = Dispositivo(id, nombre)

            database.child(id!!).setValue(dispositivo)
                .addOnSuccessListener {
                    Snackbar.make(binding.root, "Dispositivo Insertado", Snackbar.LENGTH_SHORT).show()
                    binding.etNombreDispositivo.setText("")

                }.addOnFailureListener{
                    Toast.makeText(this, "" +
                            "Dispositivo NO Insertado", Toast.LENGTH_LONG).show()
                }
        }

        binding.btnVer.setOnClickListener {
            val intent = Intent(this, HomeFragment::class.java)
            startActivity(intent)
        }


    }
}