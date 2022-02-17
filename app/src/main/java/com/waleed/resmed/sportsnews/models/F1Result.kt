package com.waleed.resmed.sportsnews.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class F1Result(override val publicationDate: LocalDateTime,
                    override val tournament: String,
                    override val winner: String,
                    @SerialName(SECONDS) val seconds : Float,
                      ) : NewsResult(){
                          companion object{

                              const val SECONDS = "seconds"

                          }

    override fun getNews(): String {
        return "$winner wins $tournament by $seconds seconds"
    }
}
