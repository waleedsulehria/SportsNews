package com.waleed.resmed.sportsnews.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


sealed class NewsResult(
){
    @SerialName(PUBLICATIONDATE) abstract val publicationDate : LocalDateTime
    @SerialName(TOURNAMENT) abstract val tournament : String
    @SerialName(WINNER) abstract val winner : String

    abstract fun getNews() : String

    fun getPublicationDateString() = publicationDate.humanReadableString

    companion object{
        const val TOURNAMENT = "tournament"
        const val WINNER = "winner"
        const val PUBLICATIONDATE = "publicationDate"
    }
}

val LocalDateTime.humanReadableString
        get() = this.format(DateTimeFormatter.ofPattern("MMM d, yyyy h:mm:ss a"))

