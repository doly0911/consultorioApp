package co.edu.udea.saludpublica.populator

import co.edu.udea.saludpublica.R
import co.edu.udea.saludpublica.enums.PriorityEnum

class PriorityPopulator {
    companion object{
        fun populate(optionSelected : Int) : PriorityEnum{
            return when(optionSelected){
                R.id.rbton_alta -> PriorityEnum.ALTA
                R.id.rbton_media -> PriorityEnum.MEDIA
                R.id.rbton_baja -> PriorityEnum.BAJA
                else -> PriorityEnum.BAJA
            }
        }

        fun populate(priority : PriorityEnum) : Int{
            return when(priority){
                    PriorityEnum.ALTA -> R.id.rbton_alta
                    PriorityEnum.MEDIA -> R.id.rbton_media
                    PriorityEnum.BAJA -> R.id.rbton_baja
            }
        }


    }
}