package com.waleed.resmed.sportsnews.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class TennisResult(override val publicationDate: LocalDateTime,
                        override val tournament: String,
                        override val winner: String,
                        @SerialName(NUMBEROFSETS) val numberOfSets : Int,
                        @SerialName(LOOSER) val looser : String

                        ) : NewsResult() {

                            companion object{

                                const val NUMBEROFSETS = "numberOfSets"
                                const val LOOSER  = "looser"
                            }

    override fun getNews(): String {
        return "$tournament : $winner wins against $looser in $numberOfSets sets"
    }


}
