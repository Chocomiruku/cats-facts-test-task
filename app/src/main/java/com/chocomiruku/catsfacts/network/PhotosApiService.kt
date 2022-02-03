package com.chocomiruku.catsfacts.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://aws.random.cat/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


interface PhotosApiService {
    @GET("meow")
    fun getRandomPhotoAsync(): Deferred<Photo>
}


object PhotosApi {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val retrofitService = retrofit.create(PhotosApiService::class.java)
}