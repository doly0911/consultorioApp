package co.edu.udea.saludpublica.dao.solicitud

import co.edu.udea.saludpublica.models.Solicitud

interface SolicitudDao {
    fun getSolicitudes():List<Solicitud>
}