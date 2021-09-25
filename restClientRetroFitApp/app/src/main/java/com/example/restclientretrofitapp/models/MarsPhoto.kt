package com.example.restclientretrofitapp.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarsPhoto(
    @Json(name = "id") var id:String,
    @Json(name = "img_src")var img_src:String

    /**
     * names match with key names from the json objects
     *  we can change the names by using @JSON(name="img_src")  img_src:String
     */
)