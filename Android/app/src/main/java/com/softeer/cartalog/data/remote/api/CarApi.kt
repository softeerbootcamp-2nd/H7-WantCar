package com.softeer.cartalog.data.remote.api

import com.softeer.cartalog.data.model.TrimDetail
import com.softeer.cartalog.data.model.Trims
import com.softeer.cartalog.data.model.Types
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CarApi {

    @GET("models/types")
    suspend fun getTypes(@Query("trimId") trimId: Int): Response<Types>

    @GET("/models/trims/detail")
    suspend fun getTrimsDetail(
        @Query("modelTypeIds") modelTypeIds: String,
        @Query("trimId") trimId: Int
    ): Response<TrimDetail>

    @GET("/models/trims")
    suspend fun getTrims(@Query("basicModelId") basicModelId: Int): Response<Trims>

}