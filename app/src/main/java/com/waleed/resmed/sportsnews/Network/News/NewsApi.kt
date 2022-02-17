package com.waleed.resmed.sportsnews.Network.News

import com.waleed.resmed.sportsnews.Network.BaseApi
import com.waleed.resmed.sportsnews.Network.NewsNetworkResponse
import com.waleed.resmed.sportsnews.models.NewsResponse


class NewsApi : NewsProvider {
    private var newsApi : NewsApiProvider
    init {
        newsApi = BaseApi.getInstance().create(NewsApiProvider::class.java)
    }
    override suspend fun getNews(): NewsNetworkResponse<NewsResponse> {
        return NewsNetworkResponse.map(newsApi.getNews())
    }
}