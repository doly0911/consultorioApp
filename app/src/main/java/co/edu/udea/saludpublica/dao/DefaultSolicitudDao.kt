package co.edu.udea.saludpublica.dao

import co.edu.udea.saludpublica.models.Solicitud

class DefaultSolicitudDao : SolicitudDao {
    override fun getSolicitudes(): List<Solicitud> {
        val solicitudes = ArrayList<Solicitud>()
        solicitudes.add(Solicitud("104563312", "Ester Bedoya", "Ayuda", "prueba", "Media"))
        solicitudes.add(Solicitud("12358963", "Doly Jimenez", "Ayuda", "prueba", "Alta"))
        return solicitudes
    }

}