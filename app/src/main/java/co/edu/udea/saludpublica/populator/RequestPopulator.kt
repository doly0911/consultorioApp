package co.edu.udea.saludpublica.populator

import co.edu.udea.saludpublica.databinding.FragmentCreacionSolicitudBinding
import co.edu.udea.saludpublica.enums.ChannelEnum
import co.edu.udea.saludpublica.models.Request

class RequestPopulator {

    companion object{
        fun populate(request: Request, binding: FragmentCreacionSolicitudBinding){
            request.topic =  binding.editTxtAsuntoConsulta.text.toString()
            request.description = binding.editTxtDescripcionConsulta.text.toString()
            request.priority = PriorityPopulator.populate(binding.radioBtnPrioridad.checkedRadioButtonId)
            request.channel =  binding.spinner.selectedItem as ChannelEnum
        }
    }
}