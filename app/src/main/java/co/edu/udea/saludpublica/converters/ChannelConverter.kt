package co.edu.udea.saludpublica.converters

import androidx.room.TypeConverter
import co.edu.udea.saludpublica.enums.ChannelEnum

class ChannelConverter {

    @TypeConverter
    fun fromChannelEnum(value: ChannelEnum): Int{
        return value.ordinal
    }

    @TypeConverter
    fun toChannelEnum(value: Int): ChannelEnum {
        return when(value){
            0 -> ChannelEnum.CORREO
            1 -> ChannelEnum.TELEFONO
            2 -> ChannelEnum.ESCRITO
            else -> ChannelEnum.CORREO
        }
    }
}