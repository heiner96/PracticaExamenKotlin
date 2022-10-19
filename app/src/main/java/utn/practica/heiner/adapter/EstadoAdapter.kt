package utn.practica.heiner.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import utn.practica.heiner.databinding.EstadoFilaBinding
import utn.practica.heiner.model.Estado
import utn.practica.heiner.ui.home.HomeFragmentDirections

class EstadoAdapter : RecyclerView.Adapter<EstadoAdapter.EstadoViewHolder>()
{
    //la lista para presentar la informacion de los lugares
    private var listaEstados = emptyList<Estado>()

    inner class EstadoViewHolder(private val itemBinding: EstadoFilaBinding) :
        RecyclerView.ViewHolder(itemBinding.root){
        fun bind(estado: Estado){
            itemBinding.tvNombre.text = estado.nombre
            itemBinding.tvCorreoLugarFila.text = estado.capital
            itemBinding.tvTelefonoLugar.text = estado.nPoblacion.toString()
            itemBinding.vistaFila.setOnClickListener {
                val accion = HomeFragmentDirections
                    .actionNavHomeToUpdateEstadoFragment(estado)
                itemView.findNavController().navigate(accion)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstadoViewHolder {
        //creo un elemeto en memoria de una "cajita" vista_fila
        val itemBinding = EstadoFilaBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        //retorno la cajita en memoria
        return EstadoViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: EstadoViewHolder, position: Int) {
        //Obtengo el objeto que debo "dibujar" en la fila del recyclerView que "voy"
        val estadoActual = listaEstados[position]

        holder.bind(estadoActual) //LLamo a la función que efectivamente "pinta" la inflate

    }

    override fun getItemCount(): Int {
        return listaEstados.size
    }

    fun setData(estados: List<Estado>){
        this.listaEstados = estados
        notifyDataSetChanged()
    }
}