package com.rmakiyama.android.blueprint.data.api

import com.rmakiyama.android.blueprint.data.api.response.ArticleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface QiitaApiService {

    @GET("items")
    suspend fun getRecentArticle(
        @Query("page") page: Int,
        @Query("query") query: String
    ): Response<List<ArticleResponse>>

    companion object {

        const val ENDPOINT = "https://qiita.com/api/v2/"
    }
}