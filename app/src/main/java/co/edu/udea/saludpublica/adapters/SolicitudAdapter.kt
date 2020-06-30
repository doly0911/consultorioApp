package co.edu.udea.saludpublica.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.edu.udea.saludpublica.databinding.SolicitudViewBinding
import co.edu.udea.saludpublica.models.Solicitud
import co.edu.udea.saludpublica.util.PrioridadEnum

class SolicitudAdapter (private val data :List<Solicitud>, private val onclick : SolicitudAdapterOnClickListener ) : RecyclerView.Adapter<SolicitudAdapter.SolicitudViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SolicitudViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SolicitudViewBinding.inflate(inflater,parent,false)
        return SolicitudViewHolder(binding,onclick)
    }

    override fun onBindViewHolder(holder: SolicitudViewHolder, position: Int) {
        holder.bind(data[position])
    }


    override fun getItemCount(): Int {
        return this.data.size
    }

    class SolicitudViewHolder(val binding: SolicitudViewBinding, val listener: SolicitudAdapterOnClickListener ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item : Solicitud){
            with(binding){
                solicitud = item
                when (item.prioridad){
                    PrioridadEnum.ALTA -> cardFrameSolicitud.setBackgroundColor(Color.RED)
                    PrioridadEnum.MEDIA -> cardFrameSolicitud.setBackgroundColor(Color.YELLOW)
                    PrioridadEnum.BAJA -> cardFrameSolicitud.setBackgroundColor(Color.BLUE)
                }

                btnEditar.setOnClickListener{
                    listener.btnEditarOnClick(item,it)
                }

                btnResponder.setOnClickListener{
                    listener.btnResponderOnClick(item,it)
                }

                executePendingBindings()
            }
        }

    }

    interface SolicitudAdapterOnClickListener {
        fun btnEditarOnClick(solicitud: Solicitud, view : View)
        fun btnResponderOnClick(solicitud: Solicitud, view : View)
    }

}