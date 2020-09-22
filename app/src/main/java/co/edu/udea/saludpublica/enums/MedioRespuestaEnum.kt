package co.edu.udea.saludpublica.enums

enum class MedioRespuestaEnum(val value: Int) {

    CORREO(0) {
        override  fun toString() : String{
            return "Correo"
        }
    },
    TELEFONO(1){
        override  fun toString() : String{
            return "Teléfono"
        }
    },
    ESCRITO(2){
        override  fun toString() : String{
            return "Escrito"
        }
    }
}