package co.edu.udea.saludpublica.models

import co.edu.udea.saludpublica.util.PrioridadEnum

data class Solicitud(var cedula: String, var persona: String, var asunto: String,
                     var descripcion: String, var prioridad: PrioridadEnum)