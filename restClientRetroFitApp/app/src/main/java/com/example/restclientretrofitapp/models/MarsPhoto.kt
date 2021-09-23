package com.example.restclientretrofitapp.models

data class MarsPhoto(
    val id:String,
    val img_src:String

    /**
     * names match with key names from the json objects
     *  we can change the names by using @JSON(name="img_src")  img_src:String
     */


)