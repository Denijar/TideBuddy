package com.tidebuddy.network.niwa

import com.tidebuddy.network.niwa.data.TideData
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface TideApi {

    @GET("tides/data")
    suspend fun getTideData(
        @Query("lat") lat: String,
        @Query("long") long: String,
        @Query("apikey") apikey: String
    ) : Response<TideData>

    companion object {

        var BASE_URL = "https://api.niwa.co.nz/"

        fun create() : TideApi {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(TideApi::class.java)

        }
    }
}