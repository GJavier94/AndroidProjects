package com.example.restclientretrofitapp.models

import com.squareup.moshi.Json

/**
 * This is a response for a GET
 */
data class UserGETResponse (
    @Json(name="meta") val meta:Meta?,
    @Json(name="data") val data:List<User>?
    )
