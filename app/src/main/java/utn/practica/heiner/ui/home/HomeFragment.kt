package utn.practica.heiner.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import utn.practica.heiner.R
import utn.practica.heiner.adapter.EstadoAdapter
import utn.practica.heiner.databinding.FragmentHomeBinding
import utn.practica.heiner.viewmodel.EstadoViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var estadoViewModel: EstadoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        estadoViewModel = ViewModelProvider(this).get(EstadoViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.btAddLugarFab.setOnClickListener{
            findNavController().navigate(R.id.action_nav_home_to_addEstadoFragment)
        }
        //Activacion del RecyclerView
        val estadoAdatper = EstadoAdapter()
        val reciclador = binding.reciclador
        reciclador.adapter = estadoAdatper
        reciclador.layoutManager = LinearLayoutManager(requireContext())
        estadoViewModel.getEstados.observe(viewLifecycleOwner) { estados->
            estadoAdatper.setData(estados)
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}