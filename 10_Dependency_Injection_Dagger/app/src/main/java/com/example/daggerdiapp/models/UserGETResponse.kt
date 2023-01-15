package com.example.daggerdiapp.models

import com.squareup.moshi.Json

data class UserGETResponse (
    @Json(name="meta") val meta:Meta?,
    @Json(name="data") val data:List<User>?
)

