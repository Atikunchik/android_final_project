package com.federicocotogno.retrofit2example

import com.federicocotogno.retrofit2example.api.Country
import retrofit2.Call
import retrofit2.http.GET

interface ApiRequests {

    @GET("/countries")
    fun getCountry(): Call<Country>
}