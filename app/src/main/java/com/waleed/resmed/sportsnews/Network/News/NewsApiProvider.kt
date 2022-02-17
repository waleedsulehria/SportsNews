package com.waleed.resmed.sportsnews.Network.News

import com.waleed.resmed.sportsnews.models.NewsResponse
import retrofit2.Response

import retrofit2.http.POST

interface NewsApiProvider {


    @POST(RESULTS)
    suspend fun getNews() : Response<NewsResponse>

    companion object{
        const val RESULTS = "results"
    }
}