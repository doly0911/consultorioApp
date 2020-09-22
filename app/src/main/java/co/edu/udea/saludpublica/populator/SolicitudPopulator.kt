package co.edu.udea.saludpublica.populator

import co.edu.udea.saludpublica.databinding.FragmentCreacionSolicitudBinding
import co.edu.udea.saludpublica.enums.MedioRespuestaEnum
import co.edu.udea.saludpublica.models.Solicitud

class SolicitudPopulator {

    companion object{
        fun populate(solicitud: Solicitud,binding: FragmentCreacionSolicitudBinding){
            solicitud.asunto =  binding.editTxtAsuntoConsulta.text.toString()
            solicitud.descripcion = binding.editTxtDescripcionConsulta.text.toString()
            solicitud.prioridad = PrioridadPopulator.populate(binding.radioBtnPrioridad.checkedRadioButtonId)
            solicitud.medio =  binding.spinner.selectedItem as MedioRespuestaEnum
        }
    }
}