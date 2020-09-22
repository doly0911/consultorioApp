package co.edu.udea.saludpublica.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import co.edu.udea.saludpublica.R
import co.edu.udea.saludpublica.database.ConsultarioDatabase
import co.edu.udea.saludpublica.databinding.FragmentCreacionSolicitudBinding
import co.edu.udea.saludpublica.enums.MedioRespuestaEnum
import co.edu.udea.saludpublica.models.Solicitud
import co.edu.udea.saludpublica.populator.PrioridadPopulator
import co.edu.udea.saludpublica.populator.SolicitudPopulator
import co.edu.udea.saludpublica.viewmodels.CreacionSolicitudViewModel
import co.edu.udea.saludpublica.viewmodels.CreacionSolicitudViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class CreacionSolicitudFragment : Fragment() {

    private lateinit var binding : FragmentCreacionSolicitudBinding
    private lateinit var viewModel: CreacionSolicitudViewModel

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
        binding.lifecycleOwner = viewLifecycleOwner

        binding.spinner.adapter = ArrayAdapter<MedioRespuestaEnum>(
            context!!,
            android.R.layout.simple_spinner_dropdown_item,
            MedioRespuestaEnum.values()
        )
        populateActivity(solicitud)

        val database = ConsultarioDatabase.getInstance(requireContext())
        val factory = CreacionSolicitudViewModelFactory(database.solicitudDao)
        viewModel = ViewModelProvider(this, factory).get(CreacionSolicitudViewModel::class.java)
        binding.viewModel = viewModel

        binding.imageBtnGuardar.setOnClickListener{
            if(solicitud != null){
                SolicitudPopulator.populate(solicitud,binding)
                viewModel.update(solicitud)
            }else{
                val newSolicitud = Solicitud()
                newSolicitud.cedula = "123"
                newSolicitud.persona = "Doly"
                SolicitudPopulator.populate(newSolicitud,binding)
                viewModel.insert(newSolicitud)
            }
        }

        viewModel.navigateToSolicitudesFragment.observe(viewLifecycleOwner, Observer {
            if(it == true){
                this.findNavController().navigate(CreacionSolicitudFragmentDirections
                        .actionCreacionSolicitudFragmentToSolicitudesFragment())
                viewModel.doneNavigation()
            }
        })

        return binding.root
    }

    private fun populateActivity(solicitud: Solicitud?){
        binding.apply {
            editTxtAsuntoConsulta.setText(solicitud?.asunto)
            editTxtDescripcionConsulta.setText(solicitud?.descripcion)
            solicitud?.let {
                binding.radioBtnPrioridad.check(PrioridadPopulator.populate(solicitud.prioridad))
                binding.spinner.setSelection(solicitud.medio.value)
            }
        }

    }


}
