package com.example.restclientretrofitapp.models

import com.squareup.moshi.Json

data class Pagination (
    @Json( name="total") val total:Long,
    @Json( name="pages") val pages:Long,
    @Json( name="page")  val page:Long,
    @Json( name="limit") val limit:Long ,
    @Json( name="links") val links:Link,
)