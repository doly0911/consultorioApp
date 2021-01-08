package co.edu.udea.saludpublica.models

import android.os.Parcelable
import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import co.edu.udea.saludpublica.converters.MedioRespuestaConverter
import co.edu.udea.saludpublica.converters.PrioridadConverter
import co.edu.udea.saludpublica.enums.MedioRespuestaEnum
import co.edu.udea.saludpublica.enums.PrioridadEnum
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "solicitud_table")
data class Solicitud(
    @PrimaryKey(autoGenerate = true)
    var solicitudId : Long = 0L,

    @ForeignKey
        (entity = Usuario::class,
        parentColumns = ["usuarioId"],
        childColumns = ["solicitante"],
        onDelete = CASCADE)
    var solicitante: Long = 0L,

    @ColumnInfo(name = "asunto")
    var asunto: String = "",

    @ColumnInfo(name = "descripcion")
    var descripcion: String = "",

    @ColumnInfo(name = "prioridad")
    @TypeConverters(PrioridadConverter::class)
    var prioridad: PrioridadEnum = PrioridadEnum.BAJA,

    @ColumnInfo(name = "medio_respuesta")
    @TypeConverters(MedioRespuestaConverter::class)
    var medio : MedioRespuestaEnum = MedioRespuestaEnum.CORREO

) : Parcelable