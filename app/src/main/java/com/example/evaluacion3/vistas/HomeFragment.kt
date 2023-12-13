package com.example.evaluacion3.vistas
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.evaluacion3.Adapter.AdapterDispositivo
import com.example.evaluacion3.InsertarActivity
import com.example.evaluacion3.Models.Dispositivo
import com.example.evaluacion3.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private lateinit var auth: FirebaseAuth

    //ViewBinding
    private lateinit var binding: FragmentHomeBinding

    // Lista de dispositivos
    private lateinit var dispositivoList: ArrayList<Dispositivo>

    //Adaptar
    private lateinit var dispositivosReciclerView: RecyclerView

    //Firebase
    private lateinit var database: DatabaseReference

    //Adapter
    private lateinit var adapterDispositivo: AdapterDispositivo


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        auth = FirebaseAuth.getInstance()

        binding.btnAnadir.setOnClickListener {
            val intent = Intent(requireContext(), InsertarActivity::class.java)
            startActivity(intent)
        }
        return view

        dispositivosReciclerView = binding.rvDispositivos
        dispositivosReciclerView.layoutManager = LinearLayoutManager(requireContext())
        dispositivosReciclerView.setHasFixedSize(true)

        dispositivoList = arrayListOf<Dispositivo>()
        getDispositivos()

        return view

    }

    private fun getDispositivos() {

        //Ruta de dispositivos
        database = FirebaseDatabase.getInstance().getReference("Dispositivos")

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (dispositivosSnapshot in snapshot.children) {
                        val dispositivo = dispositivosSnapshot.getValue(Dispositivo::class.java)
                        dispositivoList.add(dispositivo!!)
                    }
                    adapterDispositivo = AdapterDispositivo(dispositivoList)
                    dispositivosReciclerView.adapter = adapterDispositivo


                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }



    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}