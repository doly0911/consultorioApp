package co.edu.udea.saludpublica.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.edu.udea.saludpublica.R
import co.edu.udea.saludpublica.models.Solicitud

class SolicitudAdapter : RecyclerView.Adapter<SolicitudAdapter.SolicitudViewHolder>() {

    lateinit var data: List<Solicitud>

    fun SolicitudAdapter(data : List<Solicitud> ){
        this.data = data;
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SolicitudViewHolder {
        return SolicitudViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.solicitud_view,
                parent,
                false
            )
        );
    }

    override fun onBindViewHolder(holder: SolicitudViewHolder, position: Int) {
        val solicitud: Solicitud = data.get(position)
        holder.cedula.text = solicitud.cedula
        holder.persona.text = solicitud.persona
        holder.asunto.text = solicitud.asunto
        holder.descripcion.text = solicitud.descripcion
        holder.prioridad.text = solicitud.prioridad
    }


    override fun getItemCount(): Int {
        return this.data.size
    }

    class SolicitudViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var cedula: TextView = view.findViewById(R.id.txt_cedula_value);
        var persona: TextView = view.findViewById(R.id.txt_persona_value)
        var asunto: TextView = view.findViewById(R.id.txt_asunto_value)
        var descripcion: TextView = view.findViewById(R.id.txt_descripcion_value)
        var prioridad: TextView = view.findViewById(R.id.txt_prioridad_value)

    }

}