package co.edu.udea.saludpublica.models

import androidx.room.Embedded
import androidx.room.Relation

data class UserxRequest(

    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "owner"
    )
    val requests: List<Request>
)