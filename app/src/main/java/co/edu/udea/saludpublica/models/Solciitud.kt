package co.edu.udea.saludpublica.models

import android.os.Parcelable
import co.edu.udea.saludpublica.enums.MedioRespuestaEnum
import co.edu.udea.saludpublica.enums.PrioridadEnum
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Solicitud(var cedula: String, var persona: String, var asunto: String,
                     var descripcion: String, var prioridad: PrioridadEnum, var medio : MedioRespuestaEnum) : Parcelable