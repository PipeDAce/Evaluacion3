package com.example.evaluacion3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.evaluacion3.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {

    //ViewBinding
    private lateinit var binding : ActivityMainBinding
    //Firebase auth
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar FireBase con auth
         auth = Firebase.auth


        //El login se activa al dar click al boton
        binding.btnLogin.setOnClickListener{

            //Recupera el correo y contraseña
            val correo = binding.etEmail.text.toString()
            val contrasena = binding.etPass.text.toString()

            if (correo.isEmpty()){
                binding.etEmail.error = "Ingrese un correo"
                return@setOnClickListener
            }
            if (contrasena.isEmpty()){
                binding.etPass.error = "Ingrese una contraseña"
            }

            signIn(correo, contrasena)

        }

        //Reconocer si hago click sobre btnRegistrar
        binding.btnRegistrar.setOnClickListener {
            val intent = Intent(this, RegistrarActivity::class.java)
            startActivity(intent)
        }

    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful) {
                    Toast.makeText(this, "Inicio de sesion exitoso", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, AplicacionActivity::class.java)
                    startActivity(intent)


                } else {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }

            }
    }
}