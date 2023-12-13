package com.example.evaluacion3.vistas

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.example.evaluacion3.AplicacionActivity
import com.example.evaluacion3.CambiarPassActivity
import com.example.evaluacion3.MainActivity
import com.example.evaluacion3.R
import com.example.evaluacion3.databinding.ActivityMainBinding
import com.example.evaluacion3.databinding.FragmentAboutBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AboutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AboutFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private lateinit var binding : FragmentAboutBinding
    //Firebase auth
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val view = inflater.inflate(R.layout.fragment_about, container, false)

        val btnCambiarPass = view.findViewById<Button>(R.id.CambiarPass)

        btnCambiarPass.setOnClickListener {
            val intent = Intent(requireContext(), CambiarPassActivity::class.java)
            startActivity(intent)
        }

        val Volver = view.findViewById<Button>(R.id.Volver)
        Volver.setOnClickListener {
            val intent = Intent(requireContext(), AplicacionActivity::class.java)
            startActivity(intent)
        }


        auth = Firebase.auth
        // Configurar el clic del botón para cerrar sesión



        val btnCerrar = view.findViewById<Button>(R.id.CerrarSesion)

        btnCerrar.setOnClickListener {

            context?.let { fragmentContext ->
                MaterialAlertDialogBuilder(fragmentContext)
                    .setTitle("Cerrar Sesión")
                    .setMessage("¿Desea Cerrar Sesión?")
                    .setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->
                        // Responde al botón neutral
                    }
                    .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                        auth.signOut()
                        Toast.makeText(requireContext(), "Cerró sesión", Toast.LENGTH_LONG).show()
                        activity?.finish()
                        val intent = Intent(requireContext(), MainActivity::class.java)
                        startActivity(intent)
                    }
                    .show()
            }
        }



        return view
    }






    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AboutFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AboutFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}