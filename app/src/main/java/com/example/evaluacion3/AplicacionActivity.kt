package com.example.evaluacion3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.evaluacion3.databinding.ActivityAplicacionBinding
import com.example.evaluacion3.databinding.ActivityMainBinding
import com.example.evaluacion3.vistas.AboutFragment
import com.example.evaluacion3.vistas.HomeFragment
import com.example.evaluacion3.vistas.SettingFragment
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class AplicacionActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAplicacionBinding
    //Firebase auth
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAplicacionBinding.inflate(layoutInflater) //Config ViewBinding
        setContentView(binding.root)

        //Cargar un fragment cuando inicie la app
        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, HomeFragment()).commit()
    }


        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId) {

                R.id.nav_inicio -> {
                    // Indicamos que hacer --> mostrar el fragment de home
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, HomeFragment()).commit()
                    true
                }
                R.id.nav_setting -> {
                    // Indicamos que hacer --> mostrar el fragment de settings
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, SettingFragment()).commit()
                    true

                }
                R.id.nav_about -> {
                    // Indicamos que hacer --> mostrar el fragment de about
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, AboutFragment()).commit()
                    true
                }
                else -> false

            }

        }

        binding.bottomNav.setOnItemReselectedListener {
            when(it.itemId){
                R.id.nav_inicio -> Toast.makeText(this, "Ya estas en el Inicio", Toast.LENGTH_SHORT).show()
                R.id.nav_setting -> Toast.makeText(this, "Ya estas en el Dispositivo", Toast.LENGTH_SHORT).show()
                R.id.nav_about -> Toast.makeText(this, "Ya estas en el Perfil", Toast.LENGTH_SHORT).show()
                else -> false

            }
        }

        
    }
}