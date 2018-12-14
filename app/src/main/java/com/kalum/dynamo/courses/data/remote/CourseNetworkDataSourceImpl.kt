package com.kalum.dynamo.courses.data.remote

/**
 * Created by Kalum Fernando on 12/10/2018.
 */

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kalum.dynamo.courses.data.remote.api.UdacityCoursesApiService
import com.kalum.dynamo.courses.data.remote.internal.NoConnectionException
import com.kalum.dynamo.courses.data.remote.response.CoursesResponse
import com.kalum.dynamo.courses.util.TAG

class CourseNetworkDataSourceImpl(private val udacityCoursesApiService: UdacityCoursesApiService) :
    CourseNetworkDataSource {

    private val mDownloadedCurrentCourses = MutableLiveData<CoursesResponse>()

    override val downloadedCourses: LiveData<CoursesResponse>
        get() = mDownloadedCurrentCourses

    override suspend fun fetchCourses() {
        try {
            val response =
                udacityCoursesApiService.getCourses().await()

            mDownloadedCurrentCourses.postValue(response)

        } catch (e: NoConnectionException) {
            Log.e(TAG(), "NO internet connection")
        }
    }
}