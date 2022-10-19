package utn.practica.heiner.ui.home

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.navigation.fragment.navArgs
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import utn.practica.heiner.R
import utn.practica.heiner.databinding.FragmentUpdateEstadoBinding
import utn.practica.heiner.model.Estado
import utn.practica.heiner.viewmodel.EstadoViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [updateEstadoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UpdateEstadoFragment : Fragment() {
    private var _binding: FragmentUpdateEstadoBinding? = null
    private val binding get() = _binding!!
    private lateinit var estadoViewModel: EstadoViewModel

    //Defino un argumento para obtener los argumentos pasados las fragmento
    private val args by navArgs<UpdateEstadoFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        estadoViewModel = ViewModelProvider(this).get(EstadoViewModel::class.java)
        _binding = FragmentUpdateEstadoBinding.inflate(inflater, container, false)

        binding.etNombre.setText(args.estado.nombre)
        binding.etCorreoLugar.setText(args.estado.capital)
        binding.etTelefono.setText(args.estado.nPoblacion.toString())
        binding.etWeb.setText(args.estado.continente)
        binding.etPoblacion.setText(args.estado.nDepartamentos.toString())

        binding.btUpdateLugar.setOnClickListener{
            updateLugar()
        }
        binding.btDeleteLugar.setOnClickListener{
            deleteLugar()
        }


        return binding.root

    }


    private fun deleteLugar() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.msg_delete_lugar))
        builder.setMessage(getString(R.string.msg_seguro_borrado) +" ${args.estado.nombre}?")
        builder.setNegativeButton(getString(R.string.msg_no)){_,_ ->}
        builder.setPositiveButton(getString(R.string.msg_si)){_,_ ->
            estadoViewModel.deleteEstado(args.estado)
            Toast.makeText(requireContext(),getString(R.string.msg_lugar_deleted), Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateEstadoFragment_to_nav_home)
        }
        builder.show()
    }

    private fun updateLugar() {
        val nombre = binding.etNombre.text.toString()
        if(nombre.isNotEmpty()){//se puede agregar un lugar
            val nombre_capital = binding.etCorreoLugar.text.toString()
            val nPoblacion = binding.etTelefono.text.toString().toInt()
            val continente = binding.etWeb.text.toString()
            val departamentos = binding.etPoblacion.text.toString().toInt()

            val estado = Estado(args.estado.id,nombre,nombre_capital,nPoblacion,continente,departamentos)
            estadoViewModel.saveEstado(estado)
            Toast.makeText(requireContext(),getText(R.string.msg_lugar_added), Toast.LENGTH_SHORT)
            findNavController().navigate(R.id.action_updateEstadoFragment_to_nav_home)
        }
        else{//sino no se puede modificar el lugar


            Toast.makeText(requireContext(),getText(R.string.msg_data), Toast.LENGTH_LONG)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}