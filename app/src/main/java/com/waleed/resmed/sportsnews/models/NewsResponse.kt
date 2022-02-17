package com.waleed.resmed.sportsnews.models

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(

    @SerialName(F1RESULTS) val f1Results : List<F1Result>,
    @SerialName(NBARESULTS) val nbaResults : List<NBAResult>,
    @SerialName(TENNIS) val Tennis : List<TennisResult>,
    ) {
    companion object{
        const val F1RESULTS = "f1Results"
        const val NBARESULTS = "nbaResults"
        const val TENNIS = "Tennis"
    }
}