package co.edu.udea.saludpublica.fragments.respuesta


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import co.edu.udea.saludpublica.R
import co.edu.udea.saludpublica.databinding.FragmentRespuestaBinding
import co.edu.udea.saludpublica.fragments.creacionsolicitud.CreacionSolicitudFragmentArgs
import co.edu.udea.saludpublica.models.Solicitud
import com.github.aakira.expandablelayout.ExpandableRelativeLayout


/**
 * A simple [Fragment] subclass.
 */
class RespuestaFragment : Fragment() {

    lateinit var binding : FragmentRespuestaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args = arguments?.let {
            CreacionSolicitudFragmentArgs.fromBundle(
                it
            )
        }
        val solicitud : Solicitud? = args?.solicitud

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentRespuestaBinding>(inflater, R.layout.fragment_respuesta, container, false)
        populateFragment(solicitud)

        val layoutDatosSolicitud : ExpandableRelativeLayout = binding.expandablelayoutDatosSolicitud
        val layoutRespuestaSolicitud : ExpandableRelativeLayout = binding.expandablelayoutRespuestaSolicitud
        val layoutTrazabilidad : ExpandableRelativeLayout = binding.expandablelayoutTrazabilidad

        toogleLayout(binding.btnDatosSolicitud,layoutDatosSolicitud )
        toogleLayout(binding.btnRespuestaSolicitud,layoutRespuestaSolicitud )
        toogleLayout(binding.btnTrazabilidad,layoutTrazabilidad )

        collapseLayout(arrayListOf(layoutRespuestaSolicitud,layoutTrazabilidad))

        return binding.root
    }

    private fun toogleLayout(view : View, layout : ExpandableRelativeLayout){
        view.setOnClickListener{
            layout.toggle()
        }
    }

    private fun collapseLayout(layouts : List<ExpandableRelativeLayout>){
        layouts.forEach{
            it.collapse()
        }
    }

    private fun populateFragment(solicitud: Solicitud?){
        binding.layoutDatosSolicitud.apply {
            txtAsuntoValue.text = solicitud?.asunto
            txtDescripcionValue.text = solicitud?.descripcion
            txtMedioRespuestaValue.text = solicitud?.medio.toString()
            txtPrioridadValue.text = solicitud?.prioridad.toString()
        }

        binding.layoutDatosSolicitud.datosSolicitudLayout.apply {

        }

    }



}