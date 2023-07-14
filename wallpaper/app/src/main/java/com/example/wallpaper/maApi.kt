package com.example.myapplication
import com.example.wallpaper.myImage
import retrofit2.Call
import retrofit2.http.GET
interface myapi {
    @GET("character/")
    fun getData():Call<myImage>
}