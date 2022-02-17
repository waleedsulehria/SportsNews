package com.waleed.resmed.sportsnews.Network.News

import com.waleed.resmed.sportsnews.Network.NewsNetworkResponse
import com.waleed.resmed.sportsnews.models.NewsResponse
import com.waleed.resmed.sportsnews.models.NewsResult

interface NewsProvider {
    suspend fun getNews() : NewsNetworkResponse<NewsResponse>
}