package co.edu.udea.saludpublica.converters

import androidx.room.TypeConverter
import co.edu.udea.saludpublica.enums.PriorityEnum

class PriorityConverter {

    @TypeConverter
    fun fromPriorityEnum(enum: PriorityEnum): Int{
        return enum.value
    }

    @TypeConverter
    fun toPriorityEnum(value: Int): PriorityEnum{
        return when(value){
            0 -> PriorityEnum.BAJA
            1 -> PriorityEnum.MEDIA
            2 -> PriorityEnum.ALTA
            else -> PriorityEnum.ALTA
        }
    }
}