package com.federicocotogno.retrofit2example

import com.federicocotogno.retrofit2example.api.Country
import com.federicocotogno.retrofit2example.api.Statistics
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiRequests {

    @GET("/countries")
    fun getCountries(): Call<List<Country>>

    @GET("/country/{name}")
    fun getCountryStats(@Path("name") name: String): Call<List<Statistics>>
}