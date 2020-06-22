package co.edu.udea.saludpublica.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import co.edu.udea.saludpublica.R
import co.edu.udea.saludpublica.databinding.FragmentSolicitudesBinding

/**
 * A simple [Fragment] subclass.
 */
class SolicitudesFragment : Fragment() {

    private lateinit var binding: FragmentSolicitudesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentSolicitudesBinding>(inflater, R.layout.fragment_solicitudes, container, false)
        return binding.root
    }


}
