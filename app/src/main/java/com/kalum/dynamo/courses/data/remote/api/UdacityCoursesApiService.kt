package com.kalum.dynamo.courses.data.remote.api

/**
 * Created by Kalum Fernando on 12/10/2018.
 */

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kalum.dynamo.courses.data.remote.ConnectivityInterceptor
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import com.kalum.dynamo.courses.data.remote.response.CoursesResponse
import java.util.concurrent.TimeUnit


interface UdacityCoursesApiService {

    @GET("/public-api/v0/courses")
    fun getCourses(
    ): Deferred<CoursesResponse>

    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): UdacityCoursesApiService {

            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UdacityCoursesApiService::class.java)
        }

        private const val BASE_URL = "https://www.udacity.com"

    }

}