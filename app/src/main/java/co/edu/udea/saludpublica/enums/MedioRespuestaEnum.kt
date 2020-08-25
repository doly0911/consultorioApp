package co.edu.udea.saludpublica.enums

enum class MedioRespuestaEnum {

    CORREO {
        override  fun toString() : String{
            return "Correo"
        }
    },
    TELEFONO{
        override  fun toString() : String{
            return "Teléfono"
        }
    },
    ESCRITO{
        override  fun toString() : String{
            return "Escrito"
        }
    }
}