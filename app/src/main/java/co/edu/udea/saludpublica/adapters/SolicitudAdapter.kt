package co.edu.udea.saludpublica.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.edu.udea.saludpublica.databinding.SolicitudViewBinding
import co.edu.udea.saludpublica.enums.PrioridadEnum
import co.edu.udea.saludpublica.models.Solicitud

class SolicitudAdapter(private val onclick: SolicitudAdapterOnClickListener)
    : RecyclerView.Adapter<SolicitudAdapter.SolicitudViewHolder>() {


    var data = listOf<Solicitud>()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SolicitudViewHolder {
        return SolicitudViewHolder.from(this, parent)
    }

    override fun onBindViewHolder(holder: SolicitudViewHolder, position: Int) {
        holder.bind(data[position])
    }


    override fun getItemCount(): Int {
        return this.data.size
    }

    class SolicitudViewHolder private constructor(val binding: SolicitudViewBinding, private val listener: SolicitudAdapterOnClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Solicitud) {
            with(binding) {
                solicitud = item
                when (item.prioridad) {
                    PrioridadEnum.ALTA -> cardFrameSolicitud.setBackgroundColor(Color.RED)
                    PrioridadEnum.MEDIA -> cardFrameSolicitud.setBackgroundColor(Color.YELLOW)
                    PrioridadEnum.BAJA -> cardFrameSolicitud.setBackgroundColor(Color.BLUE)
                }

                btnEditar.setOnClickListener {
                    listener.btnEditarOnClick(item, it)
                }

                btnResponder.setOnClickListener {
                    listener.btnResponderOnClick(item, it)
                }

                btnEliminar.setOnClickListener {
                    listener.btnEliminarOnClick(item, it)
                }

                executePendingBindings()
            }
        }

        companion object {
            fun from(solicitudAdapter: SolicitudAdapter, parent: ViewGroup): SolicitudViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = SolicitudViewBinding.inflate(inflater, parent, false)
                return SolicitudViewHolder(binding, solicitudAdapter.onclick)
            }
        }

    }

    interface SolicitudAdapterOnClickListener {
        fun btnEditarOnClick(solicitud: Solicitud, view: View)
        fun btnResponderOnClick(solicitud: Solicitud, view: View)
        fun btnEliminarOnClick(solicitud: Solicitud, view: View)

    }


}