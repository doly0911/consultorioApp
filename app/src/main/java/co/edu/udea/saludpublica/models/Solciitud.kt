package co.edu.udea.saludpublica.models

data class Solicitud(var cedula: String, var persona: String, var asunto: String,
                     var descripcion: String, var prioridad: String)