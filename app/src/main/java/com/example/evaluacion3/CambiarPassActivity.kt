package com.example.evaluacion3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.evaluacion3.databinding.ActivityCambiarPassBinding
import com.example.evaluacion3.databinding.FragmentAboutBinding
import com.example.evaluacion3.vistas.AboutFragment
import com.google.firebase.Firebase
import com.google.firebase.auth.EmailAuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class CambiarPassActivity : AppCompatActivity() {

    //Activar ViewBinding
    private lateinit var binding: ActivityCambiarPassBinding

    // Activar firebase auth
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCambiarPassBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar FireBase

        auth = Firebase.auth

        // Boton para cancelar el cambio de contraseña y volver al perfil
        binding.btnCancelar.setOnClickListener {
            val intent = Intent(this, AboutFragment::class.java)
            startActivity(intent)
        }

        binding.btnConfirmPass.setOnClickListener {

            //Obtener usuario actual
            val user = Firebase.auth.currentUser!!

            //Obtener los datos del formulario
            val tilPassActual: String = binding.etContraActual.text.toString()
            val tilNewPass: String = binding.etNewPass.text.toString()
            val tilConfirmPass: String = binding.etConfirmPass.text.toString()

            // Validar que los campos no sean nulos

            //Reconectar al usuario actual
            val credential = EmailAuthProvider
                .getCredential(user!!.email.toString(), tilPassActual)

            user.reauthenticate(credential)
                .addOnCompleteListener {
                    if (it.isSuccessful) {

                        //Cambiar contraseña
                        Toast.makeText(this, "Cambiando la contraseña", Toast.LENGTH_LONG).show()
                        if (tilNewPass == tilConfirmPass) {
                            user!!.updatePassword(tilNewPass)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        Toast.makeText(this, "Datos actualizados", Toast.LENGTH_LONG).show()
                                        val intent = Intent(this, ActivityCambiarPassBinding::class.java)
                                        startActivity(intent)
                                    }
                                }
                                }else{
                                Toast.makeText(this, "Contraseñas no coinciden", Toast.LENGTH_LONG)
                                    .show()
                            }


                        } else {
                            Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show()
                        }
                    }
                }
        }
    }

