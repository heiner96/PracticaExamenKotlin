package utn.practica.heiner.ui.home

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import utn.practica.heiner.R
import utn.practica.heiner.databinding.FragmentAddEstadoBinding
import utn.practica.heiner.model.Estado
import utn.practica.heiner.viewmodel.EstadoViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddEstadoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddEstadoFragment : Fragment()
{
    private var _binding: FragmentAddEstadoBinding? = null
    private val binding get() = _binding!!
    private lateinit var estadoViewModel: EstadoViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        estadoViewModel = ViewModelProvider(this).get(EstadoViewModel::class.java)
        _binding = FragmentAddEstadoBinding.inflate(inflater, container, false)
        binding.btAddLugar.setOnClickListener{
            addLugar()
        }
        return binding.root
    }


    private fun addLugar() {
        val nombre = binding.etNombre.text.toString()
        if(nombre.isNotEmpty()){//se puede agregar un lugar
            val nombre_capital = binding.etCorreoLugar.text.toString()
            val nPoblacion = binding.etTelefono.text.toString().toInt()
            val continente = binding.etWeb.text.toString()
            val departamentos = binding.etPoblacion.text.toString().toInt()

            val estado = Estado(0,nombre,nombre_capital,nPoblacion,continente,departamentos)
            estadoViewModel.saveEstado(estado)
            Toast.makeText(requireContext(),getText(R.string.msg_lugar_added), Toast.LENGTH_SHORT)
            findNavController().navigate(R.id.action_addEstadoFragment_to_nav_home)
        }
        else{//sino no se puede agregar el lugar


            Toast.makeText(requireContext(),getText(R.string.msg_data), Toast.LENGTH_LONG)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}