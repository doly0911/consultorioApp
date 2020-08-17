package co.edu.udea.saludpublica.dao

import co.edu.udea.saludpublica.enums.MedioRespuestaEnum
import co.edu.udea.saludpublica.models.Solicitud
import co.edu.udea.saludpublica.enums.PrioridadEnum

class DefaultSolicitudDao : SolicitudDao {
    override fun getSolicitudes(): List<Solicitud> {
        val solicitudes = ArrayList<Solicitud>()
        solicitudes.add(Solicitud("104563312", "Ester Bedoya", "Ayuda",
            "prueba", PrioridadEnum.BAJA, MedioRespuestaEnum.CORREO))
        solicitudes.add(Solicitud("12358963", "Doly Jimenez", "Ayuda",
            "prueba", PrioridadEnum.ALTA, MedioRespuestaEnum.TELEFONO))
        solicitudes.add(Solicitud("1563328", "Bernardo Restrepo", "Ayuda",
            "prueba", PrioridadEnum.MEDIA, MedioRespuestaEnum.ESCRITO))
        return solicitudes
    }

}