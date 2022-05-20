package com.federicocotogno.retrofit2example.api

import com.federicocotogno.retrofit2example.ApiRequests
import com.federicocotogno.retrofit2example.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val api = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(ApiRequests::class.java)
