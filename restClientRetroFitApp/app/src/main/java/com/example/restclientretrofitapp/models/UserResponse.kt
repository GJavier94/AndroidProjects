package com.example.restclientretrofitapp.models

import com.squareup.moshi.Json


data class UserResponse (
    @Json(name="meta") val meta:Meta?,
    @Json(name="data") val data:List<User>?
    )
