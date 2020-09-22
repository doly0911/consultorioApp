package co.edu.udea.saludpublica.converters

import androidx.room.TypeConverter
import co.edu.udea.saludpublica.enums.PrioridadEnum

class PrioridadConverter {

    @TypeConverter
    fun fromPrioridadEnum(enum: PrioridadEnum): Int{
        return enum.value
    }

    @TypeConverter
    fun toPrioridadEnum(value: Int): PrioridadEnum{
        return when(value){
            0 -> PrioridadEnum.BAJA
            1 -> PrioridadEnum.MEDIA
            2 -> PrioridadEnum.ALTA
            else -> PrioridadEnum.ALTA
        }
    }
}