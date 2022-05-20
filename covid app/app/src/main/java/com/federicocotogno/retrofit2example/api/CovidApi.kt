package com.federicocotogno.retrofit2example.api

data class Country(
    val Country: String,
    val Slug: String,
    val ISO2: String
)

data class Statistics(
    val Country: String,
    val Confirmed: Int,
    val Deaths: Int,
    val Recovered: Int,
    val Active: Int,
    val Date: String
)
