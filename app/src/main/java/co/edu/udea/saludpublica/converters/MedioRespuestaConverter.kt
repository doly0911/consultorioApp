package co.edu.udea.saludpublica.converters

import androidx.room.TypeConverter
import co.edu.udea.saludpublica.enums.MedioRespuestaEnum

class MedioRespuestaConverter {

    @TypeConverter
    fun fromMedioRespuestaEnum(value: MedioRespuestaEnum): Int{
        return value.ordinal
    }

    @TypeConverter
    fun toMedioRespuestaEnum(value: Int): MedioRespuestaEnum {
        return when(value){
            0 -> MedioRespuestaEnum.CORREO
            1 -> MedioRespuestaEnum.TELEFONO
            2 -> MedioRespuestaEnum.ESCRITO
            else -> MedioRespuestaEnum.CORREO
        }
    }
}