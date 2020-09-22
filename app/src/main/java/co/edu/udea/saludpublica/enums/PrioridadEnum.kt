package co.edu.udea.saludpublica.enums

enum class PrioridadEnum(val value: Int) {
    ALTA(2) {
        override  fun toString() : String{
            return "Alta"
        }
    },
    MEDIA(1){
        override  fun toString() : String{
            return "Media"
        }
    },
    BAJA(0){
        override  fun toString() : String{
            return "Baja"
        }
    }
}