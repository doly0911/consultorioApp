package co.edu.udea.saludpublica.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import co.edu.udea.saludpublica.R
import co.edu.udea.saludpublica.adapters.SolicitudAdapter
import co.edu.udea.saludpublica.dao.DefaultSolicitudDao
import co.edu.udea.saludpublica.databinding.FragmentSolicitudesBinding
import co.edu.udea.saludpublica.models.Solicitud

/**
 * A simple [Fragment] subclass.
 */
class SolicitudesFragment : Fragment() {

    private lateinit var binding: FragmentSolicitudesBinding
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Modificar el titulo del actionBar
        (activity as AppCompatActivity).supportActionBar?.title = "Lista de Solicitudes"

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentSolicitudesBinding>(inflater, R.layout.fragment_solicitudes, container, false)

        viewManager = LinearLayoutManager(context)
        val viewAdapter = SolicitudAdapter()
        viewAdapter.SolicitudAdapter(getSolicitudes())

        recyclerView = binding.recyclerViewSolicitudes.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
        return binding.root
    }

    private fun getSolicitudes() : List<Solicitud>{
        return DefaultSolicitudDao().getSolicitudes()
    }

}
