package co.edu.udea.saludpublica.util

enum class PrioridadEnum {
    ALTA {
        override  fun toString() : String{
            return "Alta"
        }
    },
    MEDIA{
        override  fun toString() : String{
            return "Media"
        }
    },
    BAJA{
        override  fun toString() : String{
            return "Baja"
        }
    }
}