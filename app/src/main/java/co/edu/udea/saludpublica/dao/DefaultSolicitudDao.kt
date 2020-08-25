package co.edu.udea.saludpublica.dao

import co.edu.udea.saludpublica.enums.MedioRespuestaEnum
import co.edu.udea.saludpublica.models.Solicitud
import co.edu.udea.saludpublica.enums.PrioridadEnum

class DefaultSolicitudDao : SolicitudDao {
    override fun getSolicitudes(): List<Solicitud> {
        val solicitudes = ArrayList<Solicitud>()
        solicitudes.add(Solicitud("104563312", "Ester Bedoya", "Ayuda", "prueba", PrioridadEnum.BAJA))
        solicitudes.add(Solicitud("12358963", "Doly Jimenez", "Ayuda", "prueba", PrioridadEnum.ALTA))
        solicitudes.add(Solicitud("1563393328", "Juana De Arco", "Duda", "prueba", PrioridadEnum.MEDIA))
        solicitudes.add(Solicitud("657889995", "Jhonatan Orozco", "Ayuda", "prueba", PrioridadEnum.ALTA))
        solicitudes.add(Solicitud("1151768908", "Pepito Perez", "Ayuda", "prueba", PrioridadEnum.MEDIA))
        solicitudes.add(Solicitud("156339928", "Bernardo Restrepo", "Donec convallis mauris eget.", "Integer imperdiet sed libero vitae laoreet. Cras consequat nunc a dictum laoreet. Nullam vitae nulla.", PrioridadEnum.MEDIA))
        solicitudes.add(Solicitud("1567563312", "Florinda Meza", "Ayuda", "prueba", PrioridadEnum.BAJA))
        return solicitudes
    }

}