package co.edu.udea.saludpublica.populator

import co.edu.udea.saludpublica.R
import co.edu.udea.saludpublica.enums.PrioridadEnum

class PrioridadPopulator {
    companion object{
        fun populate(optionSelected : Int) : PrioridadEnum{
            return when(optionSelected){
                R.id.rbton_alta -> PrioridadEnum.ALTA
                R.id.rbton_media -> PrioridadEnum.MEDIA
                R.id.rbton_baja -> PrioridadEnum.BAJA
                else -> PrioridadEnum.BAJA
            }
        }

        fun populate(prioridad : PrioridadEnum) : Int{
            return when(prioridad){
                    PrioridadEnum.ALTA -> R.id.rbton_alta
                    PrioridadEnum.MEDIA -> R.id.rbton_media
                    PrioridadEnum.BAJA -> R.id.rbton_baja
            }
        }


    }
}