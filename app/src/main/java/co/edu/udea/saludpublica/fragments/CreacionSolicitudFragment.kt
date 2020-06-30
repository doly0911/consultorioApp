package co.edu.udea.saludpublica.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import co.edu.udea.saludpublica.R

/**
 * A simple [Fragment] subclass.
 */
class CreacionSolicitudFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Modificar el titulo del actionBar
        (activity as AppCompatActivity).supportActionBar?.title = "Nueva Solicitud"

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_creacion_solicitud, container, false)
    }


}
