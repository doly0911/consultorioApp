package co.edu.udea.saludpublica.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import co.edu.udea.saludpublica.R
import co.edu.udea.saludpublica.databinding.FragmentCreacionSolicitudBinding
import co.edu.udea.saludpublica.enums.MedioRespuestaEnum
import co.edu.udea.saludpublica.models.Solicitud
import co.edu.udea.saludpublica.enums.PrioridadEnum

/**
 * A simple [Fragment] subclass.
 */
class CreacionSolicitudFragment : Fragment() {

    private lateinit var binding : FragmentCreacionSolicitudBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val args = arguments?.let { CreacionSolicitudFragmentArgs.fromBundle(it) }
        val solicitud : Solicitud? = args?.solicitud

        //Modificar el titulo del actionBar
        (activity as AppCompatActivity).supportActionBar?.title = "Nueva Solicitud"

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_creacion_solicitud, container, false)

        binding.spinner.adapter = ArrayAdapter<MedioRespuestaEnum>(
            context!!,
            android.R.layout.simple_spinner_dropdown_item,
            MedioRespuestaEnum.values()
        )
        populateActivity(solicitud)
        return binding.root
    }

    private fun populateActivity(solicitud: Solicitud?){
        binding.apply {
            editTxtAsuntoConsulta.setText(solicitud?.asunto)
            editTxtDescripcionConsulta.setText(solicitud?.descripcion)
            when(solicitud?.prioridad){
                PrioridadEnum.ALTA -> radioBtnPrioridad.check(radioButton6.id)
                PrioridadEnum.MEDIA -> radioBtnPrioridad.check(radioButton7.id)
                PrioridadEnum.BAJA -> radioBtnPrioridad.check(radioButton8.id)
                else -> radioBtnPrioridad.check(radioButton6.id)
            }

            when(solicitud?.medio){
                MedioRespuestaEnum.CORREO -> spinner.setSelection(0)
                MedioRespuestaEnum.TELEFONO-> spinner.setSelection(1)
                MedioRespuestaEnum.ESCRITO -> spinner.setSelection(2)
            }
        }

    }


}
