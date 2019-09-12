package com.rmakiyama.android.blueprint.data.api

import com.rmakiyama.android.blueprint.data.api.response.ArticleResponse
import retrofit2.Response
import retrofit2.http.GET

internal interface QiitaApiService {

    @GET("items")
    suspend fun getRecentArticle(): Response<List<ArticleResponse>>

    companion object {

        const val ENDPOINT = "https://qiita.com/api/v2/"
    }
}