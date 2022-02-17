package com.waleed.resmed.sportsnews.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class NBAResult(override val publicationDate: LocalDateTime,
                     override val tournament: String,
                     override val winner: String,
                     @SerialName(GAMENUMBER) val gameNumber : Int,
                     @SerialName(LOOSER) val looser : String,
                     @SerialName(MVP) val mvp : String,
) : NewsResult(){

    companion object{
        const val GAMENUMBER = "gameNumber"
        const val LOOSER = "looser"
        const val MVP = "mvp"
    }

    override fun getNews(): String {
        return "$mvp leads $winner to game $gameNumber win in the $tournament"
    }
}
