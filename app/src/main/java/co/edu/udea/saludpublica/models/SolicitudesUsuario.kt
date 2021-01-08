package co.edu.udea.saludpublica.models

import androidx.room.Embedded
import androidx.room.Relation

data class SolicitudesUsuario(

    @Embedded val usuario: Usuario,
    @Relation(
        parentColumn = "usuarioId",
        entityColumn = "solicitante"
    )
    val solicitudes: List<Solicitud>
)