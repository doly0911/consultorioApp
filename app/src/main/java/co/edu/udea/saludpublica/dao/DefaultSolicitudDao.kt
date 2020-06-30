package co.edu.udea.saludpublica.dao

import co.edu.udea.saludpublica.models.Solicitud
import co.edu.udea.saludpublica.util.PrioridadEnum

class DefaultSolicitudDao : SolicitudDao {
    override fun getSolicitudes(): List<Solicitud> {
        val solicitudes = ArrayList<Solicitud>()
        solicitudes.add(Solicitud("104563312", "Ester Bedoya", "Ayuda", "prueba", PrioridadEnum.BAJA))
        solicitudes.add(Solicitud("12358963", "Doly Jimenez", "Ayuda", "prueba", PrioridadEnum.ALTA))
        solicitudes.add(Solicitud("1563328", "Bernardo Restrepo", "Ayuda", "prueba", PrioridadEnum.MEDIA))
        return solicitudes
    }

}