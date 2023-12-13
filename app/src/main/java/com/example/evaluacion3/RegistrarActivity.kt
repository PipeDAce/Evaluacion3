package com.example.evaluacion3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.evaluacion3.databinding.ActivityRegistrarBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class RegistrarActivity : AppCompatActivity() {

    //Activar viewBinding
    private lateinit var binding : ActivityRegistrarBinding
    //Auth FireBase
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Inicializar auth
        auth = Firebase.auth

        // Reconocer cuando le demos click sobre btnRegistrar
        binding.btnRegistrar.setOnClickListener{
            // Obtener los datos para el registro
            val correo: String = binding.etEmail.text.toString()
            val pass1: String = binding.etPass.text.toString()
            val pass2: String = binding.etPass2.text.toString()

            // Validar que los datos no sean vacios
            if (correo.isEmpty()){
                binding.etEmail.error = "Ingrese un correo"
                return@setOnClickListener
            }
            if (pass1.isEmpty()){
                binding.etPass.error = "Ingrese una contraseña"
            }
            if (pass2.isEmpty()){
                binding.etPass2.error = "Ingrese confirmación de contraseña"
            }

            if(pass1 == pass2){
                registrarUsuario(correo, pass1)
            }

        }


        //Reconocer si hago click sobre btnRegistrar
        binding.btnVolver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun registrarUsuario(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{
                if(it.isSuccessful){
                    Toast.makeText(this, "El usuario se ha registrado correctamente", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
                }
            }
    }
}