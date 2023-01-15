package com.example.restclientretrofitapp.models




import com.squareup.moshi.Json
/**
 * This is a response for a POST
 */
data class UserPUTResponse(
    @Json(name="meta") val meta:Meta?,
    @Json(name="data") val data:User?
)
