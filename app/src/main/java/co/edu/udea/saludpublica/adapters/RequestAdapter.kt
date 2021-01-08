package co.edu.udea.saludpublica.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import co.edu.udea.saludpublica.databinding.SolicitudViewBinding
import co.edu.udea.saludpublica.enums.PriorityEnum
import co.edu.udea.saludpublica.models.Request

class RequestAdapter(private val onclick: RequestAdapterOnClickListener)
    : ListAdapter<Request, RequestAdapter.RequestViewHolder> (RequestDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        return RequestViewHolder.from(this, parent)
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class RequestViewHolder private constructor(val binding: SolicitudViewBinding, private val listener: RequestAdapterOnClickListener)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Request) {
            with(binding) {
                solicitud = item
                when (item.priority) {
                    PriorityEnum.ALTA -> cardFrameSolicitud.setBackgroundColor(Color.RED)
                    PriorityEnum.MEDIA -> cardFrameSolicitud.setBackgroundColor(Color.YELLOW)
                    PriorityEnum.BAJA -> cardFrameSolicitud.setBackgroundColor(Color.BLUE)
                }

                btnEditar.setOnClickListener {
                    listener.btnEditOnClick(item, it)
                }

                btnResponder.setOnClickListener {
                    listener.btnForwardOnClick(item, it)
                }

                btnEliminar.setOnClickListener {
                    listener.btnRemoveOnClick(item, it)
                }

                executePendingBindings()
            }
        }

        companion object {
            fun from(requestAdapter: RequestAdapter, parent: ViewGroup): RequestViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = SolicitudViewBinding.inflate(inflater, parent, false)
                return RequestViewHolder(binding, requestAdapter.onclick)
            }
        }

    }

    interface RequestAdapterOnClickListener {
        fun btnEditOnClick(request: Request, view: View)
        fun btnForwardOnClick(request: Request, view: View)
        fun btnRemoveOnClick(request: Request, view: View)

    }


}

class RequestDiffCallback : DiffUtil.ItemCallback<Request>() {

    override fun areItemsTheSame(oldItem: Request, newItem: Request): Boolean {
      return  oldItem.requestId == newItem.requestId
    }

    override fun areContentsTheSame(oldItem: Request, newItem: Request): Boolean {
        return  oldItem == newItem
    }

}