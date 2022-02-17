package com.waleed.resmed.sportsnews.Network

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


object BaseApi {

    val baseUrl = "https://ancient-wood-1161.getsandbox.com:443/"

    fun getInstance() : Retrofit {

        val logginginterceptor = HttpLoggingInterceptor()
        logginginterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)


        val httpClient = OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
            requestBuilder.header("Content-Type", "application/json")
            chain.proceed(requestBuilder.build())
        }).addInterceptor(logginginterceptor).build()


        val gson = GsonBuilder().registerTypeAdapter(
            LocalDateTime::class.java,
            JsonDeserializer<Any?> { json, _, _ ->
                LocalDateTime.parse(json.asString, DateTimeFormatter.ofPattern("MMM d, yyyy h:mm:ss a"))
            }).create()


        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient)
            .build()
    }


}